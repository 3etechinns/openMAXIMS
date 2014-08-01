// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo.beans;

public class PatientElectiveListLiteVoBean extends ims.vo.ValueObjectBean
{
	public PatientElectiveListLiteVoBean()
	{
	}
	public PatientElectiveListLiteVoBean(ims.RefMan.vo.PatientElectiveListLiteVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.electivelist = vo.getElectiveList() == null ? null : (ims.admin.vo.beans.ElectiveListConfigurationVoBean)vo.getElectiveList().getBean();
		this.dateonlist = vo.getDateOnList() == null ? null : (ims.framework.utils.beans.DateBean)vo.getDateOnList().getBean();
		this.electiveliststatus = vo.getElectiveListStatus() == null ? null : (ims.RefMan.vo.beans.ElectiveListStatusForBookTheatreVoBean)vo.getElectiveListStatus().getBean();
		this.primaryprocedure = vo.getPrimaryProcedure() == null ? null : (ims.core.vo.beans.ProcedureLiteVoBean)vo.getPrimaryProcedure().getBean();
		this.proceduredescription = vo.getProcedureDescription();
		this.electivelistreason = vo.getElectiveListReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveListReason().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.PatientElectiveListLiteVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.electivelist = vo.getElectiveList() == null ? null : (ims.admin.vo.beans.ElectiveListConfigurationVoBean)vo.getElectiveList().getBean(map);
		this.dateonlist = vo.getDateOnList() == null ? null : (ims.framework.utils.beans.DateBean)vo.getDateOnList().getBean();
		this.electiveliststatus = vo.getElectiveListStatus() == null ? null : (ims.RefMan.vo.beans.ElectiveListStatusForBookTheatreVoBean)vo.getElectiveListStatus().getBean(map);
		this.primaryprocedure = vo.getPrimaryProcedure() == null ? null : (ims.core.vo.beans.ProcedureLiteVoBean)vo.getPrimaryProcedure().getBean(map);
		this.proceduredescription = vo.getProcedureDescription();
		this.electivelistreason = vo.getElectiveListReason() == null ? null : (ims.vo.LookupInstanceBean)vo.getElectiveListReason().getBean();
	}

	public ims.RefMan.vo.PatientElectiveListLiteVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.PatientElectiveListLiteVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.PatientElectiveListLiteVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.PatientElectiveListLiteVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.PatientElectiveListLiteVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public Integer getId()
	{
		return this.id;
	}
	public void setId(Integer value)
	{
		this.id = value;
	}
	public int getVersion()
	{
		return this.version;
	}
	public void setVersion(int value)
	{
		this.version = value;
	}
	public ims.admin.vo.beans.ElectiveListConfigurationVoBean getElectiveList()
	{
		return this.electivelist;
	}
	public void setElectiveList(ims.admin.vo.beans.ElectiveListConfigurationVoBean value)
	{
		this.electivelist = value;
	}
	public ims.framework.utils.beans.DateBean getDateOnList()
	{
		return this.dateonlist;
	}
	public void setDateOnList(ims.framework.utils.beans.DateBean value)
	{
		this.dateonlist = value;
	}
	public ims.RefMan.vo.beans.ElectiveListStatusForBookTheatreVoBean getElectiveListStatus()
	{
		return this.electiveliststatus;
	}
	public void setElectiveListStatus(ims.RefMan.vo.beans.ElectiveListStatusForBookTheatreVoBean value)
	{
		this.electiveliststatus = value;
	}
	public ims.core.vo.beans.ProcedureLiteVoBean getPrimaryProcedure()
	{
		return this.primaryprocedure;
	}
	public void setPrimaryProcedure(ims.core.vo.beans.ProcedureLiteVoBean value)
	{
		this.primaryprocedure = value;
	}
	public String getProcedureDescription()
	{
		return this.proceduredescription;
	}
	public void setProcedureDescription(String value)
	{
		this.proceduredescription = value;
	}
	public ims.vo.LookupInstanceBean getElectiveListReason()
	{
		return this.electivelistreason;
	}
	public void setElectiveListReason(ims.vo.LookupInstanceBean value)
	{
		this.electivelistreason = value;
	}

	private Integer id;
	private int version;
	private ims.admin.vo.beans.ElectiveListConfigurationVoBean electivelist;
	private ims.framework.utils.beans.DateBean dateonlist;
	private ims.RefMan.vo.beans.ElectiveListStatusForBookTheatreVoBean electiveliststatus;
	private ims.core.vo.beans.ProcedureLiteVoBean primaryprocedure;
	private String proceduredescription;
	private ims.vo.LookupInstanceBean electivelistreason;
}