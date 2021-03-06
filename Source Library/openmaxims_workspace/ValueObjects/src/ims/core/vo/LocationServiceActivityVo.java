//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
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
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.core.vo;

/**
 * Linked to core.resource.place.LocationServiceActivity business object (ID: 1007100001).
 */
public class LocationServiceActivityVo extends ims.core.resource.place.vo.LocationServiceActivityRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public LocationServiceActivityVo()
	{
	}
	public LocationServiceActivityVo(Integer id, int version)
	{
		super(id, version);
	}
	public LocationServiceActivityVo(ims.core.vo.beans.LocationServiceActivityVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.activitycontextpricings = ims.core.vo.ActivityContextPricingDetailsVoCollection.buildFromBeanCollection(bean.getActivityContextPricings());
		this.isactive = bean.getIsActive();
		this.serviceactivity = bean.getServiceActivity() == null ? null : bean.getServiceActivity().buildVo();
		this.locationservice = bean.getLocationService() == null ? null : bean.getLocationService().buildVo();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.LocationServiceActivityVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.activitycontextpricings = ims.core.vo.ActivityContextPricingDetailsVoCollection.buildFromBeanCollection(bean.getActivityContextPricings());
		this.isactive = bean.getIsActive();
		this.serviceactivity = bean.getServiceActivity() == null ? null : bean.getServiceActivity().buildVo(map);
		this.locationservice = bean.getLocationService() == null ? null : bean.getLocationService().buildVo(map);
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.LocationServiceActivityVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.LocationServiceActivityVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.LocationServiceActivityVoBean();
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
		if(fieldName.equals("ACTIVITYCONTEXTPRICINGS"))
			return getActivityContextPricings();
		if(fieldName.equals("ISACTIVE"))
			return getIsActive();
		if(fieldName.equals("SERVICEACTIVITY"))
			return getServiceActivity();
		if(fieldName.equals("LOCATIONSERVICE"))
			return getLocationService();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getActivityContextPricingsIsNotNull()
	{
		return this.activitycontextpricings != null;
	}
	public ims.core.vo.ActivityContextPricingDetailsVoCollection getActivityContextPricings()
	{
		return this.activitycontextpricings;
	}
	public void setActivityContextPricings(ims.core.vo.ActivityContextPricingDetailsVoCollection value)
	{
		this.isValidated = false;
		this.activitycontextpricings = value;
	}
	public boolean getIsActiveIsNotNull()
	{
		return this.isactive != null;
	}
	public Boolean getIsActive()
	{
		return this.isactive;
	}
	public void setIsActive(Boolean value)
	{
		this.isValidated = false;
		this.isactive = value;
	}
	public boolean getServiceActivityIsNotNull()
	{
		return this.serviceactivity != null;
	}
	public ims.core.vo.ServiceActivityVo getServiceActivity()
	{
		return this.serviceactivity;
	}
	public void setServiceActivity(ims.core.vo.ServiceActivityVo value)
	{
		this.isValidated = false;
		this.serviceactivity = value;
	}
	public boolean getLocationServiceIsNotNull()
	{
		return this.locationservice != null;
	}
	public ims.core.vo.LocationServiceVo getLocationService()
	{
		return this.locationservice;
	}
	public void setLocationService(ims.core.vo.LocationServiceVo value)
	{
		this.isValidated = false;
		this.locationservice = value;
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
		if(this.activitycontextpricings != null)
		{
			if(!this.activitycontextpricings.isValidated())
			{
				this.isBusy = false;
				return false;
			}
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
		if(this.activitycontextpricings != null)
		{
			String[] listOfOtherErrors = this.activitycontextpricings.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.isactive == null)
			listOfErrors.add("isActive is mandatory");
		if(this.serviceactivity == null)
			listOfErrors.add("ServiceActivity is mandatory");
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
	
		LocationServiceActivityVo clone = new LocationServiceActivityVo(this.id, this.version);
		
		if(this.activitycontextpricings == null)
			clone.activitycontextpricings = null;
		else
			clone.activitycontextpricings = (ims.core.vo.ActivityContextPricingDetailsVoCollection)this.activitycontextpricings.clone();
		clone.isactive = this.isactive;
		if(this.serviceactivity == null)
			clone.serviceactivity = null;
		else
			clone.serviceactivity = (ims.core.vo.ServiceActivityVo)this.serviceactivity.clone();
		if(this.locationservice == null)
			clone.locationservice = null;
		else
			clone.locationservice = (ims.core.vo.LocationServiceVo)this.locationservice.clone();
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
		if (!(LocationServiceActivityVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A LocationServiceActivityVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		LocationServiceActivityVo compareObj = (LocationServiceActivityVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getServiceActivity() == null && compareObj.getServiceActivity() != null)
				return -1;
			if(this.getServiceActivity() != null && compareObj.getServiceActivity() == null)
				return 1;
			if(this.getServiceActivity() != null && compareObj.getServiceActivity() != null)
				retVal = this.getServiceActivity().compareTo(compareObj.getServiceActivity());
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
		if(this.activitycontextpricings != null)
			count++;
		if(this.isactive != null)
			count++;
		if(this.serviceactivity != null)
			count++;
		if(this.locationservice != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 4;
	}
	protected ims.core.vo.ActivityContextPricingDetailsVoCollection activitycontextpricings;
	protected Boolean isactive;
	protected ims.core.vo.ServiceActivityVo serviceactivity;
	protected ims.core.vo.LocationServiceVo locationservice;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
