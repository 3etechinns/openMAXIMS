// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo;

/**
 * Linked to RefMan.PatientElectiveList business object (ID: 1014100020).
 */
public class PatientElectiveListForDNAAppointmentsVo extends ims.RefMan.vo.PatientElectiveListRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PatientElectiveListForDNAAppointmentsVo()
	{
	}
	public PatientElectiveListForDNAAppointmentsVo(Integer id, int version)
	{
		super(id, version);
	}
	public PatientElectiveListForDNAAppointmentsVo(ims.RefMan.vo.beans.PatientElectiveListForDNAAppointmentsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.electiveliststatus = bean.getElectiveListStatus() == null ? null : bean.getElectiveListStatus().buildVo();
		if(bean.getElectiveListStatusHistory() != null)
		{
			this.electiveliststatushistory = new ims.RefMan.vo.ElectiveListStatusRefVoCollection();
			for(int electiveliststatushistory_i = 0; electiveliststatushistory_i < bean.getElectiveListStatusHistory().length; electiveliststatushistory_i++)
			{
				this.electiveliststatushistory.add(new ims.RefMan.vo.ElectiveListStatusRefVo(new Integer(bean.getElectiveListStatusHistory()[electiveliststatushistory_i].getId()), bean.getElectiveListStatusHistory()[electiveliststatushistory_i].getVersion()));
			}
		}
		this.tcidetails = bean.getTCIDetails() == null ? null : bean.getTCIDetails().buildVo();
		if(bean.getTCIHistory() != null)
		{
			this.tcihistory = new ims.RefMan.vo.TCIForPatientElectiveListRefVoCollection();
			for(int tcihistory_i = 0; tcihistory_i < bean.getTCIHistory().length; tcihistory_i++)
			{
				this.tcihistory.add(new ims.RefMan.vo.TCIForPatientElectiveListRefVo(new Integer(bean.getTCIHistory()[tcihistory_i].getId()), bean.getTCIHistory()[tcihistory_i].getVersion()));
			}
		}
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.PatientElectiveListForDNAAppointmentsVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.electiveliststatus = bean.getElectiveListStatus() == null ? null : bean.getElectiveListStatus().buildVo(map);
		if(bean.getElectiveListStatusHistory() != null)
		{
			this.electiveliststatushistory = new ims.RefMan.vo.ElectiveListStatusRefVoCollection();
			for(int electiveliststatushistory_i = 0; electiveliststatushistory_i < bean.getElectiveListStatusHistory().length; electiveliststatushistory_i++)
			{
				this.electiveliststatushistory.add(new ims.RefMan.vo.ElectiveListStatusRefVo(new Integer(bean.getElectiveListStatusHistory()[electiveliststatushistory_i].getId()), bean.getElectiveListStatusHistory()[electiveliststatushistory_i].getVersion()));
			}
		}
		this.tcidetails = bean.getTCIDetails() == null ? null : bean.getTCIDetails().buildVo(map);
		if(bean.getTCIHistory() != null)
		{
			this.tcihistory = new ims.RefMan.vo.TCIForPatientElectiveListRefVoCollection();
			for(int tcihistory_i = 0; tcihistory_i < bean.getTCIHistory().length; tcihistory_i++)
			{
				this.tcihistory.add(new ims.RefMan.vo.TCIForPatientElectiveListRefVo(new Integer(bean.getTCIHistory()[tcihistory_i].getId()), bean.getTCIHistory()[tcihistory_i].getVersion()));
			}
		}
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.PatientElectiveListForDNAAppointmentsVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.PatientElectiveListForDNAAppointmentsVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.PatientElectiveListForDNAAppointmentsVoBean();
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
		if(fieldName.equals("ELECTIVELISTSTATUS"))
			return getElectiveListStatus();
		if(fieldName.equals("ELECTIVELISTSTATUSHISTORY"))
			return getElectiveListStatusHistory();
		if(fieldName.equals("TCIDETAILS"))
			return getTCIDetails();
		if(fieldName.equals("TCIHISTORY"))
			return getTCIHistory();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getElectiveListStatusIsNotNull()
	{
		return this.electiveliststatus != null;
	}
	public ims.RefMan.vo.ElectiveListStatusVo getElectiveListStatus()
	{
		return this.electiveliststatus;
	}
	public void setElectiveListStatus(ims.RefMan.vo.ElectiveListStatusVo value)
	{
		this.isValidated = false;
		this.electiveliststatus = value;
	}
	public boolean getElectiveListStatusHistoryIsNotNull()
	{
		return this.electiveliststatushistory != null;
	}
	public ims.RefMan.vo.ElectiveListStatusRefVoCollection getElectiveListStatusHistory()
	{
		return this.electiveliststatushistory;
	}
	public void setElectiveListStatusHistory(ims.RefMan.vo.ElectiveListStatusRefVoCollection value)
	{
		this.isValidated = false;
		this.electiveliststatushistory = value;
	}
	public boolean getTCIDetailsIsNotNull()
	{
		return this.tcidetails != null;
	}
	public ims.RefMan.vo.TCIForPatientElectiveListAppointmentDNAVo getTCIDetails()
	{
		return this.tcidetails;
	}
	public void setTCIDetails(ims.RefMan.vo.TCIForPatientElectiveListAppointmentDNAVo value)
	{
		this.isValidated = false;
		this.tcidetails = value;
	}
	public boolean getTCIHistoryIsNotNull()
	{
		return this.tcihistory != null;
	}
	public ims.RefMan.vo.TCIForPatientElectiveListRefVoCollection getTCIHistory()
	{
		return this.tcihistory;
	}
	public void setTCIHistory(ims.RefMan.vo.TCIForPatientElectiveListRefVoCollection value)
	{
		this.isValidated = false;
		this.tcihistory = value;
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
		if(this.electiveliststatus != null)
		{
			if(!this.electiveliststatus.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.tcidetails != null)
		{
			if(!this.tcidetails.isValidated())
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
		if(this.electiveliststatus == null)
			listOfErrors.add("ElectiveListStatus is mandatory");
		if(this.electiveliststatus != null)
		{
			String[] listOfOtherErrors = this.electiveliststatus.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.tcidetails != null)
		{
			String[] listOfOtherErrors = this.tcidetails.validate();
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
	
		PatientElectiveListForDNAAppointmentsVo clone = new PatientElectiveListForDNAAppointmentsVo(this.id, this.version);
		
		if(this.electiveliststatus == null)
			clone.electiveliststatus = null;
		else
			clone.electiveliststatus = (ims.RefMan.vo.ElectiveListStatusVo)this.electiveliststatus.clone();
		clone.electiveliststatushistory = this.electiveliststatushistory;
		if(this.tcidetails == null)
			clone.tcidetails = null;
		else
			clone.tcidetails = (ims.RefMan.vo.TCIForPatientElectiveListAppointmentDNAVo)this.tcidetails.clone();
		clone.tcihistory = this.tcihistory;
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
		if (!(PatientElectiveListForDNAAppointmentsVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PatientElectiveListForDNAAppointmentsVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((PatientElectiveListForDNAAppointmentsVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((PatientElectiveListForDNAAppointmentsVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.electiveliststatus != null)
			count++;
		if(this.electiveliststatushistory != null)
			count++;
		if(this.tcidetails != null)
			count++;
		if(this.tcihistory != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 4;
	}
	protected ims.RefMan.vo.ElectiveListStatusVo electiveliststatus;
	protected ims.RefMan.vo.ElectiveListStatusRefVoCollection electiveliststatushistory;
	protected ims.RefMan.vo.TCIForPatientElectiveListAppointmentDNAVo tcidetails;
	protected ims.RefMan.vo.TCIForPatientElectiveListRefVoCollection tcihistory;
	private boolean isValidated = false;
	private boolean isBusy = false;
}