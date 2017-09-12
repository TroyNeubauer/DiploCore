package com.troy.diplo.turn;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.troy.school.*;
import com.troyberry.util.serialization.TroyBuffer;

//Uses old java date API
@SuppressWarnings("deprecation")
public class TurnTimeSchoolProvider implements TurnTimeProvider {

	private DayBlock dayBlock;
	private int startMonth, startDay;
	private int turn = 0;
	private int currentMonth, currentDay;
	private int dueHour, dueMinuite;
	private OHS2017_2018SchoolYear year = OHS2017_2018SchoolYear.generateSchoolYear();
	private TurnTimeProvider endProvider;

	public TurnTimeSchoolProvider(int startMonth, int startDay, DayBlock dayBlock, int dueHour, int dueMinuite) {
		this.dayBlock = dayBlock;
		this.startMonth = startMonth;
		this.startDay = startDay;
		this.currentMonth = startDay;
		this.currentDay = startMonth;
		this.dueHour = dueHour;
		this.dueMinuite = dueMinuite;
	}

	public void checkEnd() {
		int lastMoth = currentMonth;
		for (Day day : year.getSchoolDays(currentMonth, currentDay + 1)) {
			if (day.getMonth() != lastMoth) {
				lastMoth = day.getMonth();
				currentDay = 1;
			}

			if (day.getDayBlock() == dayBlock) {
				return;// We found a day left in the school year :(
			}
		}
		if (endProvider == null) {
			endProvider = new TurnTimeRepeatableProvider(
					new Date(((currentMonth > Month.JULY.getMonthOfYear()) ? 2017 : 2018) - 1900, currentMonth, currentDay, dueHour, dueMinuite)
							.getTime(),
					TimeUnit.DAYS.toMillis(2));
		}
	}

	@Override
	public TurnTime next() {
		checkEnd();
		if (endProvider != null) {
			return endProvider.next();
		}
		currentMonth %= 12;
		int lastMoth = currentMonth;
		Day result = null;
		// System.out.print("cm " + currentMonth + " cd " + currentDay + " ");
		for (Day day : year.getSchoolDays(currentMonth, currentDay + 1)) {

			if (day.getMonth() != lastMoth) {
				// System.out.println("resetting current day old " + lastMoth + ", " + currentDay + " new month " + day.getMonth() + ", 1");
				lastMoth = day.getMonth();
				currentDay = 1;
			}

			// System.out.print("looking at day " + day + " ");
			if (day.getDayBlock() == dayBlock) {
				// System.out.print("pass");

				result = day;
				currentMonth = result.getMonth();
				currentDay = result.getDay();

				break;
			}
		}
		turn++;
		if (result != null) {
			return new TurnTime(new Date(((result.getMonth() > Month.JULY.getMonthOfYear()) ? 2017 : 2018) - 1900, result.getMonth(),
					result.getDay(), dueHour, dueMinuite).getTime());
		}
		return null;
	}

	@Override
	public String getPattern() {
		return "Starting on " + (startMonth + 1) + "/" + startDay + " new turn every " + dayBlock.name().toLowerCase() + " day";
	}

	@Override
	public int getTurn() {
		if (endProvider == null)
			return turn;
		return turn + endProvider.getTurn();
	}

	@Override
	public void read(TroyBuffer buffer) {
		this.dayBlock = DayBlock.map.get(buffer.readByte());
		this.startMonth = buffer.readInt();
		this.startDay = buffer.readInt();
		this.turn = buffer.readInt();
		this.currentMonth = buffer.readInt();
		this.currentDay = buffer.readInt();
		this.dueHour = buffer.readInt();
		this.dueMinuite = buffer.readInt();
		this.endProvider = TurnTimeProvider.readObj(buffer);
	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeByte((byte) dayBlock.ordinal());
		buffer.writeInt(startMonth);
		buffer.writeInt(startDay);
		buffer.writeInt(turn);
		buffer.writeInt(currentMonth);
		buffer.writeInt(currentDay);
		buffer.writeInt(dueHour);
		buffer.writeInt(dueMinuite);
		TurnTimeProvider.writeObj(endProvider, buffer);
	}
}
