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

package ims.admin.domain;

// Generated from form domain impl
public interface ElectiveListConfiguration extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	public ims.core.vo.HcpLiteVoCollection listConsultants(String name);

	// Generated from form domain interface definition
	public ims.core.vo.LocSiteLiteVoCollection listHospitals(String name);

	// Generated from form domain interface definition
	public ims.admin.vo.ServiceForElectiveListConfigVoCollection listServices(String name);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVo save(ims.admin.vo.ElectiveListConfigurationVo configurationToSave) throws ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVo getConfiguration(ims.core.configuration.vo.ElectiveListConfigurationRefVo configurationRef);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVoCollection search(ims.admin.vo.ElectiveListConfigSearchCriteriaVo searchCriteria);

	// Generated from form domain interface definition
	public Boolean existConfigurationWithSameName(String name, ims.core.configuration.vo.ElectiveListConfigurationRefVo waitingListConfigRef);

	// Generated from form domain interface definition
	public Boolean existConfigurationWithSameCode(String code, ims.core.configuration.vo.ElectiveListConfigurationRefVo waitingListConfigRef, ims.framework.utils.Date startDate, ims.framework.utils.Date endDate);

	// Generated from form domain interface definition
	public Boolean isConsultantMarkedAsDefaultForSameServiceForOtherConfiguration(ims.core.resource.people.vo.HcpRefVo consultantRef, ims.admin.vo.ServiceForElectiveListConfigVo serviceVo, ims.core.configuration.vo.ElectiveListConfigurationRefVo waitingListConfigRef, ims.framework.utils.Date startDate, ims.framework.utils.Date endDate);

	// Generated from form domain interface definition
	public Boolean canInactivateConfiguration(ims.core.configuration.vo.ElectiveListConfigurationRefVo configRef);

	// Generated from form domain interface definition
	public ims.core.vo.LocationLiteVoCollection listCaseNoteLocationByParentLocation(ims.core.resource.place.vo.LocSiteRefVo parentLocSite, String name);
}
