package com.yukthi.automation.ui.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.yukthi.automation.AutomationContext;
import com.yukthi.automation.Executable;
import com.yukthi.automation.IExecutionLogger;
import com.yukthi.automation.IStep;
import com.yukthi.automation.ui.common.AutomationUtils;
import com.yukthi.utils.exceptions.InvalidStateException;

/**
 * Simulates the click event on the specified button.
 * 
 * @author akiran
 */
@Executable("click")
public class ClickStep implements IStep
{
	/**
	 * The logger.
	 */
	private static Logger logger = LogManager.getLogger(ClickStep.class);
	
	/** 
	 * The failed message. 
	 **/
	private static String FAILED_MESSAGE = "Failed to find button with locator: ";

	/**
	 * locator for button.
	 */
	private String locator;
	
	@Override
	public void execute(AutomationContext context, IExecutionLogger exeLogger)
	{
		logger.trace("Clicking the button: {}", locator);

		WebElement buttonElement = AutomationUtils.findElement(context, null, locator);

		if(buttonElement == null)
		{
			exeLogger.error(FAILED_MESSAGE + locator);
			throw new NullPointerException(FAILED_MESSAGE + locator);
		}

		AutomationUtils.validateWithWait(() -> {
			try
			{
				buttonElement.click();
				return true;
			} catch(RuntimeException ex)
			{
				if(ex.getMessage().toLowerCase().contains("not clickable"))
				{
					return false;
				}

				throw ex;
			}
		} , AutomationUtils.FIVE_SECONDS, "Waiting for element to be clickable: " + locator, new InvalidStateException("Failed to click element - " + locator));
	}

	/**
	 * Gets the locator for button.
	 *
	 * @return the locator for button
	 */
	public String getLocator()
	{
		return locator;
	}

	/**
	 * Sets the locator for button.
	 *
	 * @param locator
	 *            the new locator for button
	 */
	public void setLocator(String locator)
	{
		this.locator = locator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append("[");

		builder.append("Locator: ").append(locator);

		builder.append("]");
		return builder.toString();
	}
}
