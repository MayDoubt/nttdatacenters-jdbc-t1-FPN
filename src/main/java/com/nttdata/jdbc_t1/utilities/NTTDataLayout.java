package com.nttdata.jdbc_t1.utilities;

import java.util.Date;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

public class NTTDataLayout extends LayoutBase<ILoggingEvent> {

	/** Imprime prefijo */
	private String prefix;
	
	/** Imprime nombre de hilo */
	private boolean printThreadName = Boolean.TRUE;
	
	
	/**
	 * Genera el layout
	 * 
	 * @param event
	 * @return String
	 */
	@Override
	public String doLayout(ILoggingEvent event) {
		
		final StringBuilder sb = new StringBuilder();
		sb.append(InterfaceUtils.toStrBuilder((new Date(event.getTimeStamp()).toString()), " ", prefix, " ", event.getLevel().toString()));
		
		if(printThreadName) {
			sb.append(InterfaceUtils.toStrBuilder(" [", event.getThreadName(),"] "));
		}
		
		sb.append((InterfaceUtils.toStrBuilder(" ", event.getLoggerName(), " - ", event.getFormattedMessage(), "\n")));
		
		return sb.toString();
	}

}
