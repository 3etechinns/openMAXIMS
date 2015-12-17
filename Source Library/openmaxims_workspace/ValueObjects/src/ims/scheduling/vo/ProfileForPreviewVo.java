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

package ims.scheduling.vo;

/**
 * Linked to Scheduling.Sch_Profile business object (ID: 1005100001).
 */
public class ProfileForPreviewVo extends ims.scheduling.vo.Sch_ProfileRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public ProfileForPreviewVo()
	{
	}
	public ProfileForPreviewVo(Integer id, int version)
	{
		super(id, version);
	}
	public ProfileForPreviewVo(ims.scheduling.vo.beans.ProfileForPreviewVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.name = bean.getName();
		this.isfixed = bean.getIsFixed();
		this.efffrm = bean.getEffFrm() == null ? null : bean.getEffFrm().buildDate();
		this.effto = bean.getEffTo() == null ? null : bean.getEffTo().buildDate();
		this.intervaltype = bean.getIntervalType() == null ? null : ims.scheduling.vo.lookups.Profile_Interval_Type.buildLookup(bean.getIntervalType());
		this.isactive = bean.getIsActive();
		this.schlocation = bean.getSchLocation() == null ? null : bean.getSchLocation().buildVo();
		this.service = bean.getService() == null ? null : bean.getService().buildVo();
		this.profiletype = bean.getProfileType() == null ? null : ims.scheduling.vo.lookups.SchProfileType.buildLookup(bean.getProfileType());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.scheduling.vo.beans.ProfileForPreviewVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.name = bean.getName();
		this.isfixed = bean.getIsFixed();
		this.efffrm = bean.getEffFrm() == null ? null : bean.getEffFrm().buildDate();
		this.effto = bean.getEffTo() == null ? null : bean.getEffTo().buildDate();
		this.intervaltype = bean.getIntervalType() == null ? null : ims.scheduling.vo.lookups.Profile_Interval_Type.buildLookup(bean.getIntervalType());
		this.isactive = bean.getIsActive();
		this.schlocation = bean.getSchLocation() == null ? null : bean.getSchLocation().buildVo(map);
		this.service = bean.getService() == null ? null : bean.getService().buildVo(map);
		this.profiletype = bean.getProfileType() == null ? null : ims.scheduling.vo.lookups.SchProfileType.buildLookup(bean.getProfileType());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.scheduling.vo.beans.ProfileForPreviewVoBean bean = null;
		if(map != null)
			bean = (ims.scheduling.vo.beans.ProfileForPreviewVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.scheduling.vo.beans.ProfileForPreviewVoBean();
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
		if(fieldName.equals("NAME"))
			return getName();
		if(fieldName.equals("ISFIXED"))
			return getIsFixed();
		if(fieldName.equals("EFFFRM"))
			return getEffFrm();
		if(fieldName.equals("EFFTO"))
			return getEffTo();
		if(fieldName.equals("INTERVALTYPE"))
			return getIntervalType();
		if(fieldName.equals("ISACTIVE"))
			return getIsActive();
		if(fieldName.equals("SCHLOCATION"))
			return getSchLocation();
		if(fieldName.equals("SERVICE"))
			return getService();
		if(fieldName.equals("PROFILETYPE"))
			return getProfileType();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getNameIsNotNull()
	{
		return this.name != null;
	}
	public String getName()
	{
		return this.name;
	}
	public static int getNameMaxLength()
	{
		return 100;
	}
	public void setName(String value)
	{
		this.isValidated = false;
		this.name = value;
	}
	public boolean getIsFixedIsNotNull()
	{
		return this.isfixed != null;
	}
	public Boolean getIsFixed()
	{
		return this.isfixed;
	}
	public void setIsFixed(Boolean value)
	{
		this.isValidated = false;
		this.isfixed = value;
	}
	public boolean getEffFrmIsNotNull()
	{
		return this.efffrm != null;
	}
	public ims.framework.utils.Date getEffFrm()
	{
		return this.efffrm;
	}
	public void setEffFrm(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.efffrm = value;
	}
	public boolean getEffToIsNotNull()
	{
		return this.effto != null;
	}
	public ims.framework.utils.Date getEffTo()
	{
		return this.effto;
	}
	public void setEffTo(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.effto = value;
	}
	public boolean getIntervalTypeIsNotNull()
	{
		return this.intervaltype != null;
	}
	public ims.scheduling.vo.lookups.Profile_Interval_Type getIntervalType()
	{
		return this.intervaltype;
	}
	public void setIntervalType(ims.scheduling.vo.lookups.Profile_Interval_Type value)
	{
		this.isValidated = false;
		this.intervaltype = value;
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
	public boolean getSchLocationIsNotNull()
	{
		return this.schlocation != null;
	}
	public ims.core.vo.LocationLiteVo getSchLocation()
	{
		return this.schlocation;
	}
	public void setSchLocation(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.schlocation = value;
	}
	public boolean getServiceIsNotNull()
	{
		return this.service != null;
	}
	public ims.core.vo.ServiceShortVo getService()
	{
		return this.service;
	}
	public void setService(ims.core.vo.ServiceShortVo value)
	{
		this.isValidated = false;
		this.service = value;
	}
	public boolean getProfileTypeIsNotNull()
	{
		return this.profiletype != null;
	}
	public ims.scheduling.vo.lookups.SchProfileType getProfileType()
	{
		return this.profiletype;
	}
	public void setProfileType(ims.scheduling.vo.lookups.SchProfileType value)
	{
		this.isValidated = false;
		this.profiletype = value;
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
		if(this.name == null || this.name.length() == 0)
			listOfErrors.add("Name is mandatory");
		else if(this.name.length() > 100)
			listOfErrors.add("The length of the field [name] in the value object [ims.scheduling.vo.ProfileForPreviewVo] is too big. It should be less or equal to 100");
		if(this.isfixed == null)
			listOfErrors.add("isFixed is mandatory");
		if(this.efffrm == null)
			listOfErrors.add("EffFrm is mandatory");
		if(this.schlocation == null)
			listOfErrors.add("SchLocation is mandatory");
		if(this.service == null)
			listOfErrors.add("Service is mandatory");
		if(this.profiletype == null)
			listOfErrors.add("ProfileType is mandatory");
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
	
		ProfileForPreviewVo clone = new ProfileForPreviewVo(this.id, this.version);
		
		clone.name = this.name;
		clone.isfixed = this.isfixed;
		if(this.efffrm == null)
			clone.efffrm = null;
		else
			clone.efffrm = (ims.framework.utils.Date)this.efffrm.clone();
		if(this.effto == null)
			clone.effto = null;
		else
			clone.effto = (ims.framework.utils.Date)this.effto.clone();
		if(this.intervaltype == null)
			clone.intervaltype = null;
		else
			clone.intervaltype = (ims.scheduling.vo.lookups.Profile_Interval_Type)this.intervaltype.clone();
		clone.isactive = this.isactive;
		if(this.schlocation == null)
			clone.schlocation = null;
		else
			clone.schlocation = (ims.core.vo.LocationLiteVo)this.schlocation.clone();
		if(this.service == null)
			clone.service = null;
		else
			clone.service = (ims.core.vo.ServiceShortVo)this.service.clone();
		if(this.profiletype == null)
			clone.profiletype = null;
		else
			clone.profiletype = (ims.scheduling.vo.lookups.SchProfileType)this.profiletype.clone();
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
		if (!(ProfileForPreviewVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A ProfileForPreviewVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((ProfileForPreviewVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((ProfileForPreviewVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.name != null)
			count++;
		if(this.isfixed != null)
			count++;
		if(this.efffrm != null)
			count++;
		if(this.effto != null)
			count++;
		if(this.intervaltype != null)
			count++;
		if(this.isactive != null)
			count++;
		if(this.schlocation != null)
			count++;
		if(this.service != null)
			count++;
		if(this.profiletype != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 9;
	}
	protected String name;
	protected Boolean isfixed;
	protected ims.framework.utils.Date efffrm;
	protected ims.framework.utils.Date effto;
	protected ims.scheduling.vo.lookups.Profile_Interval_Type intervaltype;
	protected Boolean isactive;
	protected ims.core.vo.LocationLiteVo schlocation;
	protected ims.core.vo.ServiceShortVo service;
	protected ims.scheduling.vo.lookups.SchProfileType profiletype;
	private boolean isValidated = false;
	private boolean isBusy = false;
}