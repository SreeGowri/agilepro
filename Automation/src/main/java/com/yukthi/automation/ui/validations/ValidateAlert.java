package com.yukthi.automation.ui.validations;

import org.openqa.selenium.WebElement;

import com.yukthi.automation.AbstractValidation;
import com.yukthi.automation.AutomationContext;
import com.yukthi.automation.Executable;
import com.yukthi.automation.IExecutionLogger;
import com.yukthi.automation.ui.common.AutomationUtils;
import com.yukthi.automation.ui.steps.WaitForStep;

/**
 * Validates alert box is displayed and closes the dialog.
 */
@Executable("validateAlert")
public class ValidateAlert extends AbstractValidation
{
	/**
	 * Expected alert message.
	 */
	private String message;
	
	/**
	 * Gets the expected alert message;.
	 *
	 * @return the expected alert message;
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the expected alert message;.
	 *
	 * @param message the new expected alert message;
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see com.yukthi.ui.automation.IValidation#execute(com.yukthi.ui.automation.AutomationContext, java.io.PrintWriter)
	 */
	@Override
	public boolean validate(AutomationContext context, IExecutionLogger exeLogger)
	{
		//wait and validate alert box is displayed
		WaitForStep waitStep = new WaitForStep();
		waitStep.setLocator("//div[@id='webutilsAlertDialog']");
		
		//ensure alert has required message
		WebElement alertBox = AutomationUtils.findElement(context, null, "id: webutilsAlertDialog");
		WebElement bodyElement = AutomationUtils.findElement(context, alertBox, "xpath: .//div[@class='modal-body']");
		String bodyText = bodyElement.getAttribute("innerHTML");
		
		if(!bodyText.equals(message))
		{
			exeLogger.error("Expected alert message '{}' is not matching with actual value - {}", message, bodyText);
			return false;
		}
		
		WebElement buttonElement = AutomationUtils.findElement(context, alertBox, "xpath: .//button");
		buttonElement.click();
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append("[");

		builder.append("Message: ").append(message);

		builder.append("]");
		return builder.toString();
	}
}
