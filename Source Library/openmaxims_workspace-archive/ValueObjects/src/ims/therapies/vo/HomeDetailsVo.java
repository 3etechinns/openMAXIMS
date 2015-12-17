//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.therapies.vo;

/**
 * Linked to therapies.homeAndEnvironmentalVisit.HomeDetails business object (ID: 1019100089).
 */
public class HomeDetailsVo extends ims.therapies.homeandenvironmentalvisit.vo.HomeDetailsRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public HomeDetailsVo()
	{
	}
	public HomeDetailsVo(Integer id, int version)
	{
		super(id, version);
	}
	public HomeDetailsVo(ims.therapies.vo.beans.HomeDetailsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.address = bean.getAddress();
		this.accommodationtype = bean.getAccommodationType() == null ? null : ims.spinalinjuries.vo.lookups.AccommodationType.buildLookup(bean.getAccommodationType());
		this.ownershipstatus = bean.getOwnershipStatus() == null ? null : ims.spinalinjuries.vo.lookups.OwnershipStatus.buildLookup(bean.getOwnershipStatus());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.therapies.vo.beans.HomeDetailsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.address = bean.getAddress();
		this.accommodationtype = bean.getAccommodationType() == null ? null : ims.spinalinjuries.vo.lookups.AccommodationType.buildLookup(bean.getAccommodationType());
		this.ownershipstatus = bean.getOwnershipStatus() == null ? null : ims.spinalinjuries.vo.lookups.OwnershipStatus.buildLookup(bean.getOwnershipStatus());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.therapies.vo.beans.HomeDetailsVoBean bean = null;
		if(map != null)
			bean = (ims.therapies.vo.beans.HomeDetailsVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.therapies.vo.beans.HomeDetailsVoBean();
			map.addValueObjectBean(this, bean);
			bean.populate(map, this);
		}
		return bean;
	}
	public Object getFieldValueByFieldName(String fieldName)
	{
		if(fieldName == null)
			throw new ims.framework.exceptions.CodingRuntimeException("Invalid field name");
		fieldName = fieldName.toUpperCase();
		if(fieldName.equals("ADDRESS"))
			return getAddress();
		if(fieldName.equals("ACCOMMODATIONTYPE"))
			return getAccommodationType();
		if(fieldName.equals("OWNERSHIPSTATUS"))
			return getOwnershipStatus();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getAddressIsNotNull()
	{
		return this.address != null;
	}
	public String getAddress()
	{
		return this.address;
	}
	public static int getAddressMaxLength()
	{
		return 255;
	}
	public void setAddress(String value)
	{
		this.isValidated = false;
		this.address = value;
	}
	public boolean getAccommodationTypeIsNotNull()
	{
		return this.accommodationtype != null;
	}
	public ims.spinalinjuries.vo.lookups.AccommodationType getAccommodationType()
	{
		return this.accommodationtype;
	}
	public void setAccommodationType(ims.spinalinjuries.vo.lookups.AccommodationType value)
	{
		this.isValidated = false;
		this.accommodationtype = value;
	}
	public boolean getOwnershipStatusIsNotNull()
	{
		return this.ownershipstatus != null;
	}
	public ims.spinalinjuries.vo.lookups.OwnershipStatus getOwnershipStatus()
	{
		return this.ownershipstatus;
	}
	public void setOwnershipStatus(ims.spinalinjuries.vo.lookups.OwnershipStatus value)
	{
		this.isValidated = false;
		this.ownershipstatus = value;
	}
	public boolean isValidated()
	{
		if(this.isBusy)
			return true;
		this.isBusy = true;
	
		if(!this.isValidated)
		{
			this.isBusy = false;
			return false;
		}
		this.isBusy = false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(this.isBusy)
			return null;
		this.isBusy = true;
	
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		if(this.address != null)
			if(this.address.length() > 255)
				listOfErrors.add("The field Address is too long. Please reduce it");
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
		{
			this.isBusy = false;
			this.isValidated = true;
			return null;
		}
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		this.isBusy = false;
		this.isValidated = false;
		return result;
	}
	public void clearIDAndVersion()
	{
		this.id = null;
		this.version = 0;
	}
	public Object clone()
	{
		if(this.isBusy)
			return this;
		this.isBusy = true;
	
		HomeDetailsVo clone = new HomeDetailsVo(this.id, this.version);
		
		clone.address = this.address;
		if(this.accommodationtype == null)
			clone.accommodationtype = null;
		else
			clone.accommodationtype = (ims.spinalinjuries.vo.lookups.AccommodationType)this.accommodationtype.clone();
		if(this.ownershipstatus == null)
			clone.ownershipstatus = null;
		else
			clone.ownershipstatus = (ims.spinalinjuries.vo.lookups.OwnershipStatus)this.ownershipstatus.clone();
		clone.isValidated = this.isValidated;
		
		this.isBusy = false;
		return clone;
	}
	public int compareTo(Object obj)
	{
		return compareTo(obj, true);
	}
	public int compareTo(Object obj, boolean caseInsensitive)
	{
		if (obj == null)
		{
			return -1;
		}
		if(caseInsensitive); // this is to avoid eclipse warning only.
		if (!(HomeDetailsVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A HomeDetailsVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		HomeDetailsVo compareObj = (HomeDetailsVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getID_HomeDetails() == null && compareObj.getID_HomeDetails() != null)
				return -1;
			if(this.getID_HomeDetails() != null && compareObj.getID_HomeDetails() == null)
				return 1;
			if(this.getID_HomeDetails() != null && compareObj.getID_HomeDetails() != null)
				retVal = this.getID_HomeDetails().compareTo(compareObj.getID_HomeDetails());
		}
		return retVal;
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.address != null)
			count++;
		if(this.accommodationtype != null)
			count++;
		if(this.ownershipstatus != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 3;
	}
	protected String address;
	protected ims.spinalinjuries.vo.lookups.AccommodationType accommodationtype;
	protected ims.spinalinjuries.vo.lookups.OwnershipStatus ownershipstatus;
	private boolean isValidated = false;
	private boolean isBusy = false;
}