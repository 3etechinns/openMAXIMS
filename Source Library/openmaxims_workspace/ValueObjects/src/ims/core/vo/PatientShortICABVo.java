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
 * Linked to core.patient.Patient business object (ID: 1001100000).
 */
public class PatientShortICABVo extends ims.core.patient.vo.PatientRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PatientShortICABVo()
	{
	}
	public PatientShortICABVo(Integer id, int version)
	{
		super(id, version);
	}
	public PatientShortICABVo(ims.core.vo.beans.PatientShortICABVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.name = bean.getName() == null ? null : bean.getName().buildVo();
		this.sex = bean.getSex() == null ? null : ims.core.vo.lookups.Sex.buildLookup(bean.getSex());
		this.address = bean.getAddress() == null ? null : bean.getAddress().buildVo();
		this.dob = bean.getDob() == null ? null : bean.getDob().buildPartialDate();
		this.dod = bean.getDod() == null ? null : bean.getDod().buildDate();
		this.identifiers = ims.core.vo.PatientIdCollection.buildFromBeanCollection(bean.getIdentifiers());
		this.isactive = bean.getIsActive();
		this.commchannels = ims.core.vo.CommChannelVoCollection.buildFromBeanCollection(bean.getCommChannels());
		this.scn = bean.getSCN();
		this.sourceofinformation = bean.getSourceOfInformation() == null ? null : ims.core.vo.lookups.RegistrationSourceOfInfo.buildLookup(bean.getSourceOfInformation());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.PatientShortICABVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.name = bean.getName() == null ? null : bean.getName().buildVo(map);
		this.sex = bean.getSex() == null ? null : ims.core.vo.lookups.Sex.buildLookup(bean.getSex());
		this.address = bean.getAddress() == null ? null : bean.getAddress().buildVo(map);
		this.dob = bean.getDob() == null ? null : bean.getDob().buildPartialDate();
		this.dod = bean.getDod() == null ? null : bean.getDod().buildDate();
		this.identifiers = ims.core.vo.PatientIdCollection.buildFromBeanCollection(bean.getIdentifiers());
		this.isactive = bean.getIsActive();
		this.commchannels = ims.core.vo.CommChannelVoCollection.buildFromBeanCollection(bean.getCommChannels());
		this.scn = bean.getSCN();
		this.sourceofinformation = bean.getSourceOfInformation() == null ? null : ims.core.vo.lookups.RegistrationSourceOfInfo.buildLookup(bean.getSourceOfInformation());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.PatientShortICABVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.PatientShortICABVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.PatientShortICABVoBean();
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
		if(fieldName.equals("SEX"))
			return getSex();
		if(fieldName.equals("ADDRESS"))
			return getAddress();
		if(fieldName.equals("DOB"))
			return getDob();
		if(fieldName.equals("DOD"))
			return getDod();
		if(fieldName.equals("IDENTIFIERS"))
			return getIdentifiers();
		if(fieldName.equals("ISACTIVE"))
			return getIsActive();
		if(fieldName.equals("COMMCHANNELS"))
			return getCommChannels();
		if(fieldName.equals("SCN"))
			return getSCN();
		if(fieldName.equals("SOURCEOFINFORMATION"))
			return getSourceOfInformation();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getNameIsNotNull()
	{
		return this.name != null;
	}
	public ims.core.vo.PersonName getName()
	{
		return this.name;
	}
	public void setName(ims.core.vo.PersonName value)
	{
		this.isValidated = false;
		this.name = value;
	}
	public boolean getSexIsNotNull()
	{
		return this.sex != null;
	}
	public ims.core.vo.lookups.Sex getSex()
	{
		return this.sex;
	}
	public void setSex(ims.core.vo.lookups.Sex value)
	{
		this.isValidated = false;
		this.sex = value;
	}
	public boolean getAddressIsNotNull()
	{
		return this.address != null;
	}
	public ims.core.vo.PersonAddress getAddress()
	{
		return this.address;
	}
	public void setAddress(ims.core.vo.PersonAddress value)
	{
		this.isValidated = false;
		this.address = value;
	}
	public boolean getDobIsNotNull()
	{
		return this.dob != null;
	}
	public ims.framework.utils.PartialDate getDob()
	{
		return this.dob;
	}
	public void setDob(ims.framework.utils.PartialDate value)
	{
		this.isValidated = false;
		this.dob = value;
	}
	public boolean getDodIsNotNull()
	{
		return this.dod != null;
	}
	public ims.framework.utils.Date getDod()
	{
		return this.dod;
	}
	public void setDod(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.dod = value;
	}
	public boolean getIdentifiersIsNotNull()
	{
		return this.identifiers != null;
	}
	public ims.core.vo.PatientIdCollection getIdentifiers()
	{
		return this.identifiers;
	}
	public void setIdentifiers(ims.core.vo.PatientIdCollection value)
	{
		this.isValidated = false;
		this.identifiers = value;
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
	public boolean getCommChannelsIsNotNull()
	{
		return this.commchannels != null;
	}
	public ims.core.vo.CommChannelVoCollection getCommChannels()
	{
		return this.commchannels;
	}
	public void setCommChannels(ims.core.vo.CommChannelVoCollection value)
	{
		this.isValidated = false;
		this.commchannels = value;
	}
	public boolean getSCNIsNotNull()
	{
		return this.scn != null;
	}
	public Integer getSCN()
	{
		return this.scn;
	}
	public void setSCN(Integer value)
	{
		this.isValidated = false;
		this.scn = value;
	}
	public boolean getSourceOfInformationIsNotNull()
	{
		return this.sourceofinformation != null;
	}
	public ims.core.vo.lookups.RegistrationSourceOfInfo getSourceOfInformation()
	{
		return this.sourceofinformation;
	}
	public void setSourceOfInformation(ims.core.vo.lookups.RegistrationSourceOfInfo value)
	{
		this.isValidated = false;
		this.sourceofinformation = value;
	}
	/**
	* addCommunicationChannel -  - adds a new communication channel to the collection of communications
	*/
	public void addCommunicationChannel(ims.core.vo.lookups.ChannelType channelType, String val)
	{
		if (commchannels == null) commchannels = new ims.core.vo.CommChannelVoCollection();
		ims.core.vo.CommChannelVo voCommChannell = getCommunicationChannel(channelType);
		if (voCommChannell != null)
		{
			if (val == null || val.length() == 0)
			{
				commchannels.remove(commchannels.indexOf(voCommChannell ));
			}
			else
			{
				if (val.length() <= CommChannelVo.getCommValueMaxLength())  // wdev-6914
					voCommChannell.setCommValue(val);
			}	
		}
		else
		{
			if (val != null && val.length() > 0 && val.length() <= 50)  // wdev-6914
			{
				voCommChannell = new ims.core.vo.CommChannelVo();
				voCommChannell .setChannelType(channelType);
				voCommChannell .setCommValue(val);
				commchannels.add(voCommChannell);
			}
				
		}
	}
	
	
	/**
	* getPatientIdentifier
	*/
		public PatientId getPatientIdentifier(ims.core.vo.lookups.PatIdType identifierType)
		{
			if (this.identifiers == null)
				return null;
			
			for (int i=0; i<this.identifiers.size(); i++)
			{
				PatientId id = identifiers.get(i);
				if (id.type.equals(identifierType))
					return id;
			}
			return null;
		}
	/**
	* getCommunicationChannel  - Returns the Communication Channel for a specified channel type
	*/
	public ims.core.vo.CommChannelVo getCommunicationChannel(ims.core.vo.lookups.ChannelType channelType)
	{
		if (commchannels == null) return null;
		for (int i = 0; i < commchannels.size(); i++)
		{
			ims.core.vo.CommChannelVo voCommChannel = commchannels.get(i);
			if (voCommChannel.getChannelType().equals(channelType))
			{
				return voCommChannel;
			}
		}
		return null;
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
		if(this.name != null)
		{
			if(!this.name.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.address != null)
		{
			if(!this.address.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.identifiers != null)
		{
			if(!this.identifiers.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.commchannels != null)
		{
			if(!this.commchannels.isValidated())
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
		if(this.name != null)
		{
			String[] listOfOtherErrors = this.name.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.address != null)
		{
			String[] listOfOtherErrors = this.address.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.identifiers != null)
		{
			String[] listOfOtherErrors = this.identifiers.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.commchannels != null)
		{
			String[] listOfOtherErrors = this.commchannels.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
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
	
		PatientShortICABVo clone = new PatientShortICABVo(this.id, this.version);
		
		if(this.name == null)
			clone.name = null;
		else
			clone.name = (ims.core.vo.PersonName)this.name.clone();
		if(this.sex == null)
			clone.sex = null;
		else
			clone.sex = (ims.core.vo.lookups.Sex)this.sex.clone();
		if(this.address == null)
			clone.address = null;
		else
			clone.address = (ims.core.vo.PersonAddress)this.address.clone();
		if(this.dob == null)
			clone.dob = null;
		else
			clone.dob = (ims.framework.utils.PartialDate)this.dob.clone();
		if(this.dod == null)
			clone.dod = null;
		else
			clone.dod = (ims.framework.utils.Date)this.dod.clone();
		if(this.identifiers == null)
			clone.identifiers = null;
		else
			clone.identifiers = (ims.core.vo.PatientIdCollection)this.identifiers.clone();
		clone.isactive = this.isactive;
		if(this.commchannels == null)
			clone.commchannels = null;
		else
			clone.commchannels = (ims.core.vo.CommChannelVoCollection)this.commchannels.clone();
		clone.scn = this.scn;
		if(this.sourceofinformation == null)
			clone.sourceofinformation = null;
		else
			clone.sourceofinformation = (ims.core.vo.lookups.RegistrationSourceOfInfo)this.sourceofinformation.clone();
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
		if (!(PatientShortICABVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PatientShortICABVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		PatientShortICABVo compareObj = (PatientShortICABVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getName() == null && compareObj.getName() != null)
				return -1;
			if(this.getName() != null && compareObj.getName() == null)
				return 1;
			if(this.getName() != null && compareObj.getName() != null)
				retVal = this.getName().compareTo(compareObj.getName());
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
		if(this.name != null)
			count++;
		if(this.sex != null)
			count++;
		if(this.address != null)
			count++;
		if(this.dob != null)
			count++;
		if(this.dod != null)
			count++;
		if(this.identifiers != null)
			count++;
		if(this.isactive != null)
			count++;
		if(this.commchannels != null)
			count++;
		if(this.scn != null)
			count++;
		if(this.sourceofinformation != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 10;
	}
	protected ims.core.vo.PersonName name;
	protected ims.core.vo.lookups.Sex sex;
	protected ims.core.vo.PersonAddress address;
	protected ims.framework.utils.PartialDate dob;
	protected ims.framework.utils.Date dod;
	protected ims.core.vo.PatientIdCollection identifiers;
	protected Boolean isactive;
	protected ims.core.vo.CommChannelVoCollection commchannels;
	protected Integer scn;
	protected ims.core.vo.lookups.RegistrationSourceOfInfo sourceofinformation;
	private boolean isValidated = false;
	private boolean isBusy = false;
}