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
 * Linked to core.admin.pas.Inpatient Episode business object (ID: 1014100000).
 */
public class InpatientEpisodeForPendingDischargesVo extends ims.core.admin.pas.vo.InpatientEpisodeRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public InpatientEpisodeForPendingDischargesVo()
	{
	}
	public InpatientEpisodeForPendingDischargesVo(Integer id, int version)
	{
		super(id, version);
	}
	public InpatientEpisodeForPendingDischargesVo(ims.core.vo.beans.InpatientEpisodeForPendingDischargesVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.pasevent = bean.getPasEvent() == null ? null : bean.getPasEvent().buildVo();
		this.bed = bean.getBed() == null ? null : bean.getBed().buildVo();
		this.estdischargedate = bean.getEstDischargeDate() == null ? null : bean.getEstDischargeDate().buildDate();
		this.isconfirmeddischarge = bean.getIsConfirmedDischarge();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.InpatientEpisodeForPendingDischargesVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.pasevent = bean.getPasEvent() == null ? null : bean.getPasEvent().buildVo(map);
		this.bed = bean.getBed() == null ? null : bean.getBed().buildVo(map);
		this.estdischargedate = bean.getEstDischargeDate() == null ? null : bean.getEstDischargeDate().buildDate();
		this.isconfirmeddischarge = bean.getIsConfirmedDischarge();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.InpatientEpisodeForPendingDischargesVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.InpatientEpisodeForPendingDischargesVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.InpatientEpisodeForPendingDischargesVoBean();
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
		if(fieldName.equals("PASEVENT"))
			return getPasEvent();
		if(fieldName.equals("BED"))
			return getBed();
		if(fieldName.equals("ESTDISCHARGEDATE"))
			return getEstDischargeDate();
		if(fieldName.equals("ISCONFIRMEDDISCHARGE"))
			return getIsConfirmedDischarge();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getPasEventIsNotNull()
	{
		return this.pasevent != null;
	}
	public ims.core.vo.PasEventVo getPasEvent()
	{
		return this.pasevent;
	}
	public void setPasEvent(ims.core.vo.PasEventVo value)
	{
		this.isValidated = false;
		this.pasevent = value;
	}
	public boolean getBedIsNotNull()
	{
		return this.bed != null;
	}
	public ims.core.vo.BedSpaceStateVo getBed()
	{
		return this.bed;
	}
	public void setBed(ims.core.vo.BedSpaceStateVo value)
	{
		this.isValidated = false;
		this.bed = value;
	}
	public boolean getEstDischargeDateIsNotNull()
	{
		return this.estdischargedate != null;
	}
	public ims.framework.utils.Date getEstDischargeDate()
	{
		return this.estdischargedate;
	}
	public void setEstDischargeDate(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.estdischargedate = value;
	}
	public boolean getIsConfirmedDischargeIsNotNull()
	{
		return this.isconfirmeddischarge != null;
	}
	public Boolean getIsConfirmedDischarge()
	{
		return this.isconfirmeddischarge;
	}
	public void setIsConfirmedDischarge(Boolean value)
	{
		this.isValidated = false;
		this.isconfirmeddischarge = value;
	}
	/**
	* getDateTimeComparator - retrieves a new instance of static class InpatientEpisodeForPendingDischargesVoDateTimeComparator
	*/
	public static InpatientEpisodeForPendingDischargesVoDateTimeComparator getDateTimeComparator(ims.framework.enumerations.SortOrder sortOrder)
	{
		return new InpatientEpisodeForPendingDischargesVo.InpatientEpisodeForPendingDischargesVoDateTimeComparator(sortOrder);
	}
	
	
	
	
	/**
	* InpatientEpisodeForPendingDischargesVoDateTimeComparator (class definition)
	*/
	public static class InpatientEpisodeForPendingDischargesVoDateTimeComparator implements java.util.Comparator
	{
		private int direction = 1;
		public InpatientEpisodeForPendingDischargesVoDateTimeComparator ()
		{
			this(ims.framework.enumerations.SortOrder.ASCENDING);
		}
		public InpatientEpisodeForPendingDischargesVoDateTimeComparator (ims.framework.enumerations.SortOrder order)
		{
			if (order == ims.framework.enumerations.SortOrder.DESCENDING)
			{
				direction = -1;
			}
		}
		public int compare(Object obj1, Object obj2)
		{
			InpatientEpisodeForPendingDischargesVo voObj1 = (InpatientEpisodeForPendingDischargesVo)obj1;
			InpatientEpisodeForPendingDischargesVo voObj2 = (InpatientEpisodeForPendingDischargesVo)obj2;
			if(voObj1.getEstDischargeDateIsNotNull() && voObj2.getEstDischargeDateIsNotNull())
				return direction*(voObj1.getEstDischargeDate().compareTo(voObj2.getEstDischargeDate()));
			else
				return direction;
		}
		public boolean equals(Object obj)
		{
			return false;
		}	
	}
	
	/**
	* isDischargeDueWithin24Hrs
	*/
	public boolean isDischargeDueWithin24Hrs()
	{
		if(estdischargedate == null)
			return false;
		
		ims.framework.utils.Date nowPlus24Hrs = new ims.framework.utils.Date();
		nowPlus24Hrs.addDay(1);
		
		if(estdischargedate.isLessOrEqualThan(nowPlus24Hrs))
			return true;
		
		return false;	
	}
	/**
	* AgeComparator
	*/
	/**
	 * WDEV-13136
	 *	Get an ascending order AgeComparator 
	 */
	public static AgeComparator getAgeComparator()
	{
		return new AgeComparator();
	}
	
	/**
	 * WDEV-13136
	 * Get an AgeComparator with the specified order
	 */
	public static AgeComparator getAgeComparator(ims.framework.enumerations.SortOrder order)
	{
		return new AgeComparator(order);
	}
	
	/**
	 * WDEV-13136
	 * @author George Josan
	 *	Age comparator
	 */
	public static class AgeComparator implements java.util.Comparator<InpatientEpisodeForPendingDischargesVo>
	{
		private int direction;
		
		public AgeComparator()
		{
			direction = 1;
		}
		
		public AgeComparator(ims.framework.enumerations.SortOrder order)
		{
			if (ims.framework.enumerations.SortOrder.ASCENDING.equals(order))
			{
				direction = 1;
			}
			else
			{
				direction = -1;
			}
		}
	
		
		public int compare(InpatientEpisodeForPendingDischargesVo o1, InpatientEpisodeForPendingDischargesVo o2)
		{
			if (o1.getPasEventIsNotNull() && o1.getPasEvent().getPatientIsNotNull() && o1.getPasEvent().getPatient().getAgeIsNotNull()
					&& o2.getPasEventIsNotNull() && o2.getPasEvent().getPatientIsNotNull() && o2.getPasEvent().getPatient().getAgeIsNotNull())
			{
				return o1.getPasEvent().getPatient().getAge().compareTo(o2.getPasEvent().getPatient().getAge()) * direction;
			}
			
			if (o1.getPasEventIsNotNull() && o1.getPasEvent().getPatientIsNotNull() && o1.getPasEvent().getPatient().getAgeIsNotNull()
					&& (!o2.getPasEventIsNotNull() || !o2.getPasEvent().getPatientIsNotNull() || !o2.getPasEvent().getPatient().getAgeIsNotNull()))
			{
				return direction;
			}
			
			if ((!o1.getPasEventIsNotNull() || !o1.getPasEvent().getPatientIsNotNull() || !o1.getPasEvent().getPatient().getAgeIsNotNull())
				&& o2.getPasEventIsNotNull() && o2.getPasEvent().getPatientIsNotNull() && o2.getPasEvent().getPatient().getAgeIsNotNull())
			{
				return -1 * direction;
			}
			
			return 0;
		}
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
		if(this.pasevent != null)
		{
			if(!this.pasevent.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.bed != null)
		{
			if(!this.bed.isValidated())
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
		if(this.pasevent != null)
		{
			String[] listOfOtherErrors = this.pasevent.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.bed != null)
		{
			String[] listOfOtherErrors = this.bed.validate();
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
	
		InpatientEpisodeForPendingDischargesVo clone = new InpatientEpisodeForPendingDischargesVo(this.id, this.version);
		
		if(this.pasevent == null)
			clone.pasevent = null;
		else
			clone.pasevent = (ims.core.vo.PasEventVo)this.pasevent.clone();
		if(this.bed == null)
			clone.bed = null;
		else
			clone.bed = (ims.core.vo.BedSpaceStateVo)this.bed.clone();
		if(this.estdischargedate == null)
			clone.estdischargedate = null;
		else
			clone.estdischargedate = (ims.framework.utils.Date)this.estdischargedate.clone();
		clone.isconfirmeddischarge = this.isconfirmeddischarge;
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
		if (!(InpatientEpisodeForPendingDischargesVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A InpatientEpisodeForPendingDischargesVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((InpatientEpisodeForPendingDischargesVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((InpatientEpisodeForPendingDischargesVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.pasevent != null)
			count++;
		if(this.bed != null)
			count++;
		if(this.estdischargedate != null)
			count++;
		if(this.isconfirmeddischarge != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 4;
	}
	protected ims.core.vo.PasEventVo pasevent;
	protected ims.core.vo.BedSpaceStateVo bed;
	protected ims.framework.utils.Date estdischargedate;
	protected Boolean isconfirmeddischarge;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
