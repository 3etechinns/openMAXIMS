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

package ims.emergency.domain.base.impl;

import ims.domain.impl.DomainImpl;

public abstract class BaseSupportServicesImpl extends DomainImpl implements ims.emergency.domain.SupportServices, ims.domain.impl.Transactional
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public void validatesaveSupportServices(ims.emergency.vo.DischargeServicesAndAdviceVo voSupportServices)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistHcps(ims.core.vo.HcpFilter filter)
	{
	}

	@SuppressWarnings("unused")
	public void validategetHcp(ims.core.vo.HcpFilter filter)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPatient(ims.core.patient.vo.PatientRefVo voPatientShort)
	{
	}

	@SuppressWarnings("unused")
	public void validategetSupportServices(ims.emergency.vo.DischargeServicesAndAdviceRefVo refVo, ims.core.admin.vo.CareContextRefVo voCCRef)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistLocationServices(ims.core.clinical.vo.ServiceRefVo service)
	{
	}

	@SuppressWarnings("unused")
	public void validatecopyPreviousSupportServiceNetwork(ims.core.patient.vo.PatientRefVo voPatRef, ims.core.admin.vo.CareContextRefVo ccRefVo, ims.core.admin.vo.EpisodeOfCareRefVo voEpisRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetLocationService(ims.core.vo.LocShortVo locShort, ims.core.clinical.vo.ServiceRefVo service)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistMembersOfStaffWithPrimaryLocations(ims.core.vo.MemberOfStaffShortVo voMemberOfStaffShortVo, ims.core.vo.lookups.HcpDisType hcpSUbtype)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistCareContexts(ims.core.patient.vo.PatientRefVo voPatientRef)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistLocationsForMemberOfStaff(ims.core.resource.people.vo.MemberOfStaffRefVo mos)
	{
	}

	@SuppressWarnings("unused")
	public void validategetGPDetails(ims.core.patient.vo.PatientRefVo patientRef)
	{
	}

	@SuppressWarnings("unused")
	public void validatelistService(ims.core.vo.lookups.ServiceCategoryCollection excludedServices)
	{
	}

	@SuppressWarnings("unused")
	public void validategetGpSurgeryByPatient(ims.core.patient.vo.PatientRefVo patient)
	{
	}

	@SuppressWarnings("unused")
	public void validatesavePatientNoSupportNetworkServicesInfo(ims.core.vo.PatientNoSupportNetworkServicesInfoVo patientCoreClinicalDataVo)
	{
	}

	@SuppressWarnings("unused")
	public void validatesavePatientNoSupportNetworkStatusInfo(ims.core.vo.PatientNoSupportNetworkStatusInfoVo voPatientNoSupportNetworkStatusInfo)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPatientNoSupportNetworkServicesInfo(ims.core.patient.vo.PatientRefVo patientRefVo)
	{
	}

	@SuppressWarnings("unused")
	public void validategetPatientNoSupportNetworkStatusInfo(ims.core.patient.vo.PatientRefVo patientRefVo)
	{
	}

	@SuppressWarnings("unused")
	public void validatecheckForAPreviousSupportService(ims.core.patient.vo.PatientRefVo voPatientRef)
	{
	}

	@SuppressWarnings("unused")
	public void validategetHCP(ims.core.resource.people.vo.HcpRefVo hcpRefVo)
	{
	}
}
