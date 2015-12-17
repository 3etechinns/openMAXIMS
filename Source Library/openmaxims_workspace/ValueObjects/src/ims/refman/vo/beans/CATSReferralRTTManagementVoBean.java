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

package ims.RefMan.vo.beans;

public class CATSReferralRTTManagementVoBean extends ims.vo.ValueObjectBean
{
	public CATSReferralRTTManagementVoBean()
	{
	}
	public CATSReferralRTTManagementVoBean(ims.RefMan.vo.CATSReferralRTTManagementVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.referraldetails = vo.getReferralDetails() == null ? null : (ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean)vo.getReferralDetails().getBean();
		this.journey = vo.getJourney() == null ? null : (ims.RefMan.vo.beans.RTTManagementPatientPathwayJourneyVoBean)vo.getJourney().getBean();
		this.current31targetdate = vo.getCurrent31TargetDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getCurrent31TargetDate().getBean();
		this.current62targetdate = vo.getCurrent62TargetDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getCurrent62TargetDate().getBean();
		this.rttclockimpacts = vo.getRTTClockImpacts() == null ? null : vo.getRTTClockImpacts().getBeanCollection();
		this.pathwayid = vo.getPathwayID();
		this.rttclockimpact = vo.getRTTClockImpact();
		this.manuallymarkedasnotrttimpact = vo.getManuallyMarkedAsNotRTTImpact();
		this.currentrttstatus = vo.getCurrentRTTStatus() == null ? null : (ims.pathways.vo.beans.PathwayRTTStatusLiteVoBean)vo.getCurrentRTTStatus().getBean();
		this.patient = vo.getPatient() == null ? null : new ims.vo.RefVoBean(vo.getPatient().getBoId(), vo.getPatient().getBoVersion());
		this.currentstatus = vo.getCurrentStatus() == null ? null : (ims.RefMan.vo.beans.CatsReferralStatusLiteVoBean)vo.getCurrentStatus().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.CATSReferralRTTManagementVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.referraldetails = vo.getReferralDetails() == null ? null : (ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean)vo.getReferralDetails().getBean(map);
		this.journey = vo.getJourney() == null ? null : (ims.RefMan.vo.beans.RTTManagementPatientPathwayJourneyVoBean)vo.getJourney().getBean(map);
		this.current31targetdate = vo.getCurrent31TargetDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getCurrent31TargetDate().getBean();
		this.current62targetdate = vo.getCurrent62TargetDate() == null ? null : (ims.framework.utils.beans.DateBean)vo.getCurrent62TargetDate().getBean();
		this.rttclockimpacts = vo.getRTTClockImpacts() == null ? null : vo.getRTTClockImpacts().getBeanCollection();
		this.pathwayid = vo.getPathwayID();
		this.rttclockimpact = vo.getRTTClockImpact();
		this.manuallymarkedasnotrttimpact = vo.getManuallyMarkedAsNotRTTImpact();
		this.currentrttstatus = vo.getCurrentRTTStatus() == null ? null : (ims.pathways.vo.beans.PathwayRTTStatusLiteVoBean)vo.getCurrentRTTStatus().getBean(map);
		this.patient = vo.getPatient() == null ? null : new ims.vo.RefVoBean(vo.getPatient().getBoId(), vo.getPatient().getBoVersion());
		this.currentstatus = vo.getCurrentStatus() == null ? null : (ims.RefMan.vo.beans.CatsReferralStatusLiteVoBean)vo.getCurrentStatus().getBean(map);
	}

	public ims.RefMan.vo.CATSReferralRTTManagementVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.RefMan.vo.CATSReferralRTTManagementVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.CATSReferralRTTManagementVo vo = null;
		if(map != null)
			vo = (ims.RefMan.vo.CATSReferralRTTManagementVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.RefMan.vo.CATSReferralRTTManagementVo();
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
	public ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean getReferralDetails()
	{
		return this.referraldetails;
	}
	public void setReferralDetails(ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean value)
	{
		this.referraldetails = value;
	}
	public ims.RefMan.vo.beans.RTTManagementPatientPathwayJourneyVoBean getJourney()
	{
		return this.journey;
	}
	public void setJourney(ims.RefMan.vo.beans.RTTManagementPatientPathwayJourneyVoBean value)
	{
		this.journey = value;
	}
	public ims.framework.utils.beans.DateBean getCurrent31TargetDate()
	{
		return this.current31targetdate;
	}
	public void setCurrent31TargetDate(ims.framework.utils.beans.DateBean value)
	{
		this.current31targetdate = value;
	}
	public ims.framework.utils.beans.DateBean getCurrent62TargetDate()
	{
		return this.current62targetdate;
	}
	public void setCurrent62TargetDate(ims.framework.utils.beans.DateBean value)
	{
		this.current62targetdate = value;
	}
	public ims.pathways.vo.beans.PathwayRTTClockImpactUndoVoBean[] getRTTClockImpacts()
	{
		return this.rttclockimpacts;
	}
	public void setRTTClockImpacts(ims.pathways.vo.beans.PathwayRTTClockImpactUndoVoBean[] value)
	{
		this.rttclockimpacts = value;
	}
	public String getPathwayID()
	{
		return this.pathwayid;
	}
	public void setPathwayID(String value)
	{
		this.pathwayid = value;
	}
	public Boolean getRTTClockImpact()
	{
		return this.rttclockimpact;
	}
	public void setRTTClockImpact(Boolean value)
	{
		this.rttclockimpact = value;
	}
	public Boolean getManuallyMarkedAsNotRTTImpact()
	{
		return this.manuallymarkedasnotrttimpact;
	}
	public void setManuallyMarkedAsNotRTTImpact(Boolean value)
	{
		this.manuallymarkedasnotrttimpact = value;
	}
	public ims.pathways.vo.beans.PathwayRTTStatusLiteVoBean getCurrentRTTStatus()
	{
		return this.currentrttstatus;
	}
	public void setCurrentRTTStatus(ims.pathways.vo.beans.PathwayRTTStatusLiteVoBean value)
	{
		this.currentrttstatus = value;
	}
	public ims.vo.RefVoBean getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.vo.RefVoBean value)
	{
		this.patient = value;
	}
	public ims.RefMan.vo.beans.CatsReferralStatusLiteVoBean getCurrentStatus()
	{
		return this.currentstatus;
	}
	public void setCurrentStatus(ims.RefMan.vo.beans.CatsReferralStatusLiteVoBean value)
	{
		this.currentstatus = value;
	}

	private Integer id;
	private int version;
	private ims.RefMan.vo.beans.ReferralLetterDetailsLiteVoBean referraldetails;
	private ims.RefMan.vo.beans.RTTManagementPatientPathwayJourneyVoBean journey;
	private ims.framework.utils.beans.DateBean current31targetdate;
	private ims.framework.utils.beans.DateBean current62targetdate;
	private ims.pathways.vo.beans.PathwayRTTClockImpactUndoVoBean[] rttclockimpacts;
	private String pathwayid;
	private Boolean rttclockimpact;
	private Boolean manuallymarkedasnotrttimpact;
	private ims.pathways.vo.beans.PathwayRTTStatusLiteVoBean currentrttstatus;
	private ims.vo.RefVoBean patient;
	private ims.RefMan.vo.beans.CatsReferralStatusLiteVoBean currentstatus;
}