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

package ims.core.vo.beans;

public class PatientProcedureWebServiceVoBean extends ims.vo.ValueObjectBean
{
	public PatientProcedureWebServiceVoBean()
	{
	}
	public PatientProcedureWebServiceVoBean(ims.core.vo.PatientProcedureWebServiceVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.procedure = vo.getProcedure() == null ? null : (ims.core.vo.beans.ProcedureLiteVoBean)vo.getProcedure().getBean();
		this.proceduredescription = vo.getProcedureDescription();
		this.procedurestatus = vo.getProcedureStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureStatus().getBean();
		this.dateplanned = vo.getDatePlanned() == null ? null : (ims.framework.utils.beans.PartialDateBean)vo.getDatePlanned().getBean();
		this.procedureurgency = vo.getProcedureUrgency() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureUrgency().getBean();
		this.sitetext = vo.getSiteText();
		this.procedureoutcome = vo.getProcedureOutcome() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureOutcome().getBean();
		this.peformedby = vo.getPeformedBy() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getPeformedBy().getBean();
		this.surgeonsgrade = vo.getSurgeonsGrade() == null ? null : (ims.vo.LookupInstanceBean)vo.getSurgeonsGrade().getBean();
		this.location = vo.getLocation();
		this.specialty = vo.getSpecialty() == null ? null : (ims.vo.LookupInstanceBean)vo.getSpecialty().getBean();
		this.infosource = vo.getInfoSource() == null ? null : (ims.vo.LookupInstanceBean)vo.getInfoSource().getBean();
		this.confirmedstatus = vo.getConfirmedStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getConfirmedStatus().getBean();
		this.confirmedby = vo.getConfirmedBy() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getConfirmedBy().getBean();
		this.confirmeddatetime = vo.getConfirmedDateTime() == null ? null : (ims.framework.utils.beans.DateTimeBean)vo.getConfirmedDateTime().getBean();
		this.authoringinformation = vo.getAuthoringInformation() == null ? null : (ims.core.vo.beans.AuthoringInformationVoBean)vo.getAuthoringInformation().getBean();
		this.procsite = vo.getProcSite() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcSite().getBean();
		this.proclaterality = vo.getProcLaterality() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcLaterality().getBean();
		this.procdate = vo.getProcDate() == null ? null : (ims.framework.utils.beans.PartialDateBean)vo.getProcDate().getBean();
		this.proctime = vo.getProcTime() == null ? null : (ims.framework.utils.beans.TimeBean)vo.getProcTime().getBean();
		this.cancelleddate = vo.getCancelledDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getCancelledDate().getBean();
		this.cancelledreason = vo.getCancelledReason();
		this.notes = vo.getNotes();
		this.hcppresent = vo.getHCPPresent() == null ? null : vo.getHCPPresent().getBeanCollection();
		this.procenddate = vo.getProcEndDate() == null ? null : (ims.framework.utils.beans.PartialDateBean)vo.getProcEndDate().getBean();
		this.procendtime = vo.getProcEndTime() == null ? null : (ims.framework.utils.beans.TimeBean)vo.getProcEndTime().getBean();
		this.procedureintent = vo.getProcedureIntent() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureIntent().getBean();
		this.uniquelinerefno = vo.getUniqueLineRefNo();
		this.plannedproc = vo.getPlannedProc() == null ? null : new ims.vo.RefVoBean(vo.getPlannedProc().getBoId(), vo.getPlannedProc().getBoVersion());
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.PatientProcedureWebServiceVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.procedure = vo.getProcedure() == null ? null : (ims.core.vo.beans.ProcedureLiteVoBean)vo.getProcedure().getBean(map);
		this.proceduredescription = vo.getProcedureDescription();
		this.procedurestatus = vo.getProcedureStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureStatus().getBean();
		this.dateplanned = vo.getDatePlanned() == null ? null : (ims.framework.utils.beans.PartialDateBean)vo.getDatePlanned().getBean();
		this.procedureurgency = vo.getProcedureUrgency() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureUrgency().getBean();
		this.sitetext = vo.getSiteText();
		this.procedureoutcome = vo.getProcedureOutcome() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureOutcome().getBean();
		this.peformedby = vo.getPeformedBy() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getPeformedBy().getBean(map);
		this.surgeonsgrade = vo.getSurgeonsGrade() == null ? null : (ims.vo.LookupInstanceBean)vo.getSurgeonsGrade().getBean();
		this.location = vo.getLocation();
		this.specialty = vo.getSpecialty() == null ? null : (ims.vo.LookupInstanceBean)vo.getSpecialty().getBean();
		this.infosource = vo.getInfoSource() == null ? null : (ims.vo.LookupInstanceBean)vo.getInfoSource().getBean();
		this.confirmedstatus = vo.getConfirmedStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getConfirmedStatus().getBean();
		this.confirmedby = vo.getConfirmedBy() == null ? null : (ims.core.vo.beans.HcpLiteVoBean)vo.getConfirmedBy().getBean(map);
		this.confirmeddatetime = vo.getConfirmedDateTime() == null ? null : (ims.framework.utils.beans.DateTimeBean)vo.getConfirmedDateTime().getBean();
		this.authoringinformation = vo.getAuthoringInformation() == null ? null : (ims.core.vo.beans.AuthoringInformationVoBean)vo.getAuthoringInformation().getBean(map);
		this.procsite = vo.getProcSite() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcSite().getBean();
		this.proclaterality = vo.getProcLaterality() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcLaterality().getBean();
		this.procdate = vo.getProcDate() == null ? null : (ims.framework.utils.beans.PartialDateBean)vo.getProcDate().getBean();
		this.proctime = vo.getProcTime() == null ? null : (ims.framework.utils.beans.TimeBean)vo.getProcTime().getBean();
		this.cancelleddate = vo.getCancelledDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getCancelledDate().getBean();
		this.cancelledreason = vo.getCancelledReason();
		this.notes = vo.getNotes();
		this.hcppresent = vo.getHCPPresent() == null ? null : vo.getHCPPresent().getBeanCollection();
		this.procenddate = vo.getProcEndDate() == null ? null : (ims.framework.utils.beans.PartialDateBean)vo.getProcEndDate().getBean();
		this.procendtime = vo.getProcEndTime() == null ? null : (ims.framework.utils.beans.TimeBean)vo.getProcEndTime().getBean();
		this.procedureintent = vo.getProcedureIntent() == null ? null : (ims.vo.LookupInstanceBean)vo.getProcedureIntent().getBean();
		this.uniquelinerefno = vo.getUniqueLineRefNo();
		this.plannedproc = vo.getPlannedProc() == null ? null : new ims.vo.RefVoBean(vo.getPlannedProc().getBoId(), vo.getPlannedProc().getBoVersion());
	}

	public ims.core.vo.PatientProcedureWebServiceVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.core.vo.PatientProcedureWebServiceVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.PatientProcedureWebServiceVo vo = null;
		if(map != null)
			vo = (ims.core.vo.PatientProcedureWebServiceVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.core.vo.PatientProcedureWebServiceVo();
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
	public ims.core.vo.beans.ProcedureLiteVoBean getProcedure()
	{
		return this.procedure;
	}
	public void setProcedure(ims.core.vo.beans.ProcedureLiteVoBean value)
	{
		this.procedure = value;
	}
	public String getProcedureDescription()
	{
		return this.proceduredescription;
	}
	public void setProcedureDescription(String value)
	{
		this.proceduredescription = value;
	}
	public ims.vo.LookupInstanceBean getProcedureStatus()
	{
		return this.procedurestatus;
	}
	public void setProcedureStatus(ims.vo.LookupInstanceBean value)
	{
		this.procedurestatus = value;
	}
	public ims.framework.utils.beans.PartialDateBean getDatePlanned()
	{
		return this.dateplanned;
	}
	public void setDatePlanned(ims.framework.utils.beans.PartialDateBean value)
	{
		this.dateplanned = value;
	}
	public ims.vo.LookupInstanceBean getProcedureUrgency()
	{
		return this.procedureurgency;
	}
	public void setProcedureUrgency(ims.vo.LookupInstanceBean value)
	{
		this.procedureurgency = value;
	}
	public String getSiteText()
	{
		return this.sitetext;
	}
	public void setSiteText(String value)
	{
		this.sitetext = value;
	}
	public ims.vo.LookupInstanceBean getProcedureOutcome()
	{
		return this.procedureoutcome;
	}
	public void setProcedureOutcome(ims.vo.LookupInstanceBean value)
	{
		this.procedureoutcome = value;
	}
	public ims.core.vo.beans.HcpLiteVoBean getPeformedBy()
	{
		return this.peformedby;
	}
	public void setPeformedBy(ims.core.vo.beans.HcpLiteVoBean value)
	{
		this.peformedby = value;
	}
	public ims.vo.LookupInstanceBean getSurgeonsGrade()
	{
		return this.surgeonsgrade;
	}
	public void setSurgeonsGrade(ims.vo.LookupInstanceBean value)
	{
		this.surgeonsgrade = value;
	}
	public String getLocation()
	{
		return this.location;
	}
	public void setLocation(String value)
	{
		this.location = value;
	}
	public ims.vo.LookupInstanceBean getSpecialty()
	{
		return this.specialty;
	}
	public void setSpecialty(ims.vo.LookupInstanceBean value)
	{
		this.specialty = value;
	}
	public ims.vo.LookupInstanceBean getInfoSource()
	{
		return this.infosource;
	}
	public void setInfoSource(ims.vo.LookupInstanceBean value)
	{
		this.infosource = value;
	}
	public ims.vo.LookupInstanceBean getConfirmedStatus()
	{
		return this.confirmedstatus;
	}
	public void setConfirmedStatus(ims.vo.LookupInstanceBean value)
	{
		this.confirmedstatus = value;
	}
	public ims.core.vo.beans.HcpLiteVoBean getConfirmedBy()
	{
		return this.confirmedby;
	}
	public void setConfirmedBy(ims.core.vo.beans.HcpLiteVoBean value)
	{
		this.confirmedby = value;
	}
	public ims.framework.utils.beans.DateTimeBean getConfirmedDateTime()
	{
		return this.confirmeddatetime;
	}
	public void setConfirmedDateTime(ims.framework.utils.beans.DateTimeBean value)
	{
		this.confirmeddatetime = value;
	}
	public ims.core.vo.beans.AuthoringInformationVoBean getAuthoringInformation()
	{
		return this.authoringinformation;
	}
	public void setAuthoringInformation(ims.core.vo.beans.AuthoringInformationVoBean value)
	{
		this.authoringinformation = value;
	}
	public ims.vo.LookupInstanceBean getProcSite()
	{
		return this.procsite;
	}
	public void setProcSite(ims.vo.LookupInstanceBean value)
	{
		this.procsite = value;
	}
	public ims.vo.LookupInstanceBean getProcLaterality()
	{
		return this.proclaterality;
	}
	public void setProcLaterality(ims.vo.LookupInstanceBean value)
	{
		this.proclaterality = value;
	}
	public ims.framework.utils.beans.PartialDateBean getProcDate()
	{
		return this.procdate;
	}
	public void setProcDate(ims.framework.utils.beans.PartialDateBean value)
	{
		this.procdate = value;
	}
	public ims.framework.utils.beans.TimeBean getProcTime()
	{
		return this.proctime;
	}
	public void setProcTime(ims.framework.utils.beans.TimeBean value)
	{
		this.proctime = value;
	}
	public ims.framework.utils.beans.DateBean getCancelledDate()
	{
		return this.cancelleddate;
	}
	public void setCancelledDate(ims.framework.utils.beans.DateBean value)
	{
		this.cancelleddate = value;
	}
	public String getCancelledReason()
	{
		return this.cancelledreason;
	}
	public void setCancelledReason(String value)
	{
		this.cancelledreason = value;
	}
	public String getNotes()
	{
		return this.notes;
	}
	public void setNotes(String value)
	{
		this.notes = value;
	}
	public ims.core.vo.beans.HcpLiteVoBean[] getHCPPresent()
	{
		return this.hcppresent;
	}
	public void setHCPPresent(ims.core.vo.beans.HcpLiteVoBean[] value)
	{
		this.hcppresent = value;
	}
	public ims.framework.utils.beans.PartialDateBean getProcEndDate()
	{
		return this.procenddate;
	}
	public void setProcEndDate(ims.framework.utils.beans.PartialDateBean value)
	{
		this.procenddate = value;
	}
	public ims.framework.utils.beans.TimeBean getProcEndTime()
	{
		return this.procendtime;
	}
	public void setProcEndTime(ims.framework.utils.beans.TimeBean value)
	{
		this.procendtime = value;
	}
	public ims.vo.LookupInstanceBean getProcedureIntent()
	{
		return this.procedureintent;
	}
	public void setProcedureIntent(ims.vo.LookupInstanceBean value)
	{
		this.procedureintent = value;
	}
	public String getUniqueLineRefNo()
	{
		return this.uniquelinerefno;
	}
	public void setUniqueLineRefNo(String value)
	{
		this.uniquelinerefno = value;
	}
	public ims.vo.RefVoBean getPlannedProc()
	{
		return this.plannedproc;
	}
	public void setPlannedProc(ims.vo.RefVoBean value)
	{
		this.plannedproc = value;
	}

	private Integer id;
	private int version;
	private ims.core.vo.beans.ProcedureLiteVoBean procedure;
	private String proceduredescription;
	private ims.vo.LookupInstanceBean procedurestatus;
	private ims.framework.utils.beans.PartialDateBean dateplanned;
	private ims.vo.LookupInstanceBean procedureurgency;
	private String sitetext;
	private ims.vo.LookupInstanceBean procedureoutcome;
	private ims.core.vo.beans.HcpLiteVoBean peformedby;
	private ims.vo.LookupInstanceBean surgeonsgrade;
	private String location;
	private ims.vo.LookupInstanceBean specialty;
	private ims.vo.LookupInstanceBean infosource;
	private ims.vo.LookupInstanceBean confirmedstatus;
	private ims.core.vo.beans.HcpLiteVoBean confirmedby;
	private ims.framework.utils.beans.DateTimeBean confirmeddatetime;
	private ims.core.vo.beans.AuthoringInformationVoBean authoringinformation;
	private ims.vo.LookupInstanceBean procsite;
	private ims.vo.LookupInstanceBean proclaterality;
	private ims.framework.utils.beans.PartialDateBean procdate;
	private ims.framework.utils.beans.TimeBean proctime;
	private ims.framework.utils.beans.DateBean cancelleddate;
	private String cancelledreason;
	private String notes;
	private ims.core.vo.beans.HcpLiteVoBean[] hcppresent;
	private ims.framework.utils.beans.PartialDateBean procenddate;
	private ims.framework.utils.beans.TimeBean procendtime;
	private ims.vo.LookupInstanceBean procedureintent;
	private String uniquelinerefno;
	private ims.vo.RefVoBean plannedproc;
}