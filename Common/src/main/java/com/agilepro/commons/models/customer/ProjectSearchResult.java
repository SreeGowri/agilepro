package com.agilepro.commons.models.customer;

import java.util.Date;

import com.yukthi.persistence.repository.annotations.Field;
import com.yukthi.webutils.common.annotations.Model;
import com.yukthi.webutils.common.annotations.NonDisplayable;

/**
 * The Class ProjectSearchResult.
 * 
 * @author Pritam
 */
@Model
public class ProjectSearchResult
{
	/**
	 * Id.
	 */
	@NonDisplayable
	@Field(value = "id")
	private long id;

	/**
	 * Name of the project.
	 */
	@Field(value = "name")
	private String name;

	/**
	 * The start date.
	 **/
	@Field(value = "startDate")
	private Date startDate;

	/**
	 * The end date.
	 **/
	@Field(value = "endDate")
	private Date endDate;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
}
