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

package ims.core.vo;

/**
 * Linked to core.configuration.PatientKioskMessage business object (ID: 1028100047).
 */
public class PatientKioskMessageVo extends ims.core.configuration.vo.PatientKioskMessageRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PatientKioskMessageVo()
	{
	}
	public PatientKioskMessageVo(Integer id, int version)
	{
		super(id, version);
	}
	public PatientKioskMessageVo(ims.core.vo.beans.PatientKioskMessageVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.messagetype = bean.getMessageType() == null ? null : ims.admin.vo.lookups.PatientKioskMessages.buildLookup(bean.getMessageType());
		this.messagetext = bean.getMessageText();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.PatientKioskMessageVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.messagetype = bean.getMessageType() == null ? null : ims.admin.vo.lookups.PatientKioskMessages.buildLookup(bean.getMessageType());
		this.messagetext = bean.getMessageText();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.PatientKioskMessageVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.PatientKioskMessageVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.PatientKioskMessageVoBean();
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
		if(fieldName.equals("MESSAGETYPE"))
			return getMessageType();
		if(fieldName.equals("MESSAGETEXT"))
			return getMessageText();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getMessageTypeIsNotNull()
	{
		return this.messagetype != null;
	}
	public ims.admin.vo.lookups.PatientKioskMessages getMessageType()
	{
		return this.messagetype;
	}
	public void setMessageType(ims.admin.vo.lookups.PatientKioskMessages value)
	{
		this.isValidated = false;
		this.messagetype = value;
	}
	public boolean getMessageTextIsNotNull()
	{
		return this.messagetext != null;
	}
	public String getMessageText()
	{
		return this.messagetext;
	}
	public static int getMessageTextMaxLength()
	{
		return 500;
	}
	public void setMessageText(String value)
	{
		this.isValidated = false;
		this.messagetext = value;
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
		if(this.messagetype == null)
			listOfErrors.add("MessageType is mandatory");
		if(this.messagetext == null || this.messagetext.length() == 0)
			listOfErrors.add("MessageText is mandatory");
		else if(this.messagetext.length() > 500)
			listOfErrors.add("The length of the field [messagetext] in the value object [ims.core.vo.PatientKioskMessageVo] is too big. It should be less or equal to 500");
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
	
		PatientKioskMessageVo clone = new PatientKioskMessageVo(this.id, this.version);
		
		if(this.messagetype == null)
			clone.messagetype = null;
		else
			clone.messagetype = (ims.admin.vo.lookups.PatientKioskMessages)this.messagetype.clone();
		clone.messagetext = this.messagetext;
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
		if (!(PatientKioskMessageVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PatientKioskMessageVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((PatientKioskMessageVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((PatientKioskMessageVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.messagetype != null)
			count++;
		if(this.messagetext != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 2;
	}
	protected ims.admin.vo.lookups.PatientKioskMessages messagetype;
	protected String messagetext;
	private boolean isValidated = false;
	private boolean isBusy = false;
}