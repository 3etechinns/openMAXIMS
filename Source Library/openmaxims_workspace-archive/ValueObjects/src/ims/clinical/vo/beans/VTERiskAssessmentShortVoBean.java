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

package ims.clinical.vo.beans;

public class VTERiskAssessmentShortVoBean extends ims.vo.ValueObjectBean
{
	public VTERiskAssessmentShortVoBean()
	{
	}
	public VTERiskAssessmentShortVoBean(ims.clinical.vo.VTERiskAssessmentShortVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.patient = vo.getPatient() == null ? null : (ims.core.vo.beans.PatientForVTERiskAssessmentVoBean)vo.getPatient().getBean();
		this.authoringinformation = vo.getAuthoringInformation() == null ? null : (ims.core.vo.beans.AuthoringInformationVoBean)vo.getAuthoringInformation().getBean();
		this.vteassessmentstatus = vo.getVTEAssessmentStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getVTEAssessmentStatus().getBean();
		this.recordinginformation = vo.getRecordingInformation() == null ? null : (ims.core.vo.beans.RecordingUserInformationVoBean)vo.getRecordingInformation().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.clinical.vo.VTERiskAssessmentShortVo vo)
	{
		this.id = vo.getBoId();
		this.version = vo.getBoVersion();
		this.patient = vo.getPatient() == null ? null : (ims.core.vo.beans.PatientForVTERiskAssessmentVoBean)vo.getPatient().getBean(map);
		this.authoringinformation = vo.getAuthoringInformation() == null ? null : (ims.core.vo.beans.AuthoringInformationVoBean)vo.getAuthoringInformation().getBean(map);
		this.vteassessmentstatus = vo.getVTEAssessmentStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getVTEAssessmentStatus().getBean();
		this.recordinginformation = vo.getRecordingInformation() == null ? null : (ims.core.vo.beans.RecordingUserInformationVoBean)vo.getRecordingInformation().getBean(map);
	}

	public ims.clinical.vo.VTERiskAssessmentShortVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.clinical.vo.VTERiskAssessmentShortVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.clinical.vo.VTERiskAssessmentShortVo vo = null;
		if(map != null)
			vo = (ims.clinical.vo.VTERiskAssessmentShortVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.clinical.vo.VTERiskAssessmentShortVo();
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
	public ims.core.vo.beans.PatientForVTERiskAssessmentVoBean getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.core.vo.beans.PatientForVTERiskAssessmentVoBean value)
	{
		this.patient = value;
	}
	public ims.core.vo.beans.AuthoringInformationVoBean getAuthoringInformation()
	{
		return this.authoringinformation;
	}
	public void setAuthoringInformation(ims.core.vo.beans.AuthoringInformationVoBean value)
	{
		this.authoringinformation = value;
	}
	public ims.vo.LookupInstanceBean getVTEAssessmentStatus()
	{
		return this.vteassessmentstatus;
	}
	public void setVTEAssessmentStatus(ims.vo.LookupInstanceBean value)
	{
		this.vteassessmentstatus = value;
	}
	public ims.core.vo.beans.RecordingUserInformationVoBean getRecordingInformation()
	{
		return this.recordinginformation;
	}
	public void setRecordingInformation(ims.core.vo.beans.RecordingUserInformationVoBean value)
	{
		this.recordinginformation = value;
	}

	private Integer id;
	private int version;
	private ims.core.vo.beans.PatientForVTERiskAssessmentVoBean patient;
	private ims.core.vo.beans.AuthoringInformationVoBean authoringinformation;
	private ims.vo.LookupInstanceBean vteassessmentstatus;
	private ims.core.vo.beans.RecordingUserInformationVoBean recordinginformation;
}
