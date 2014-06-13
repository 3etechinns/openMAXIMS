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

package ims.coe.domain.base.impl;

import ims.domain.impl.DomainImpl;

public abstract class BaseMNAInputImpl extends DomainImpl implements ims.nursing.domain.MNAInput, ims.domain.impl.Transactional
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public void validategetRecord(Integer recordID)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveRecord(ims.nursing.vo.MiniNutritionalAssessment voMiniNutritionalAssessment)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveEatingAssessmentComponent(ims.nursing.vo.AssessmentHeaderInfo assessmentVo, ims.nursing.vo.AssessmentComponent componentVo, ims.core.vo.CareContextShortVo careContextVo)
	{
	}

	@SuppressWarnings("unused")
	public void validategetLastMetrics(ims.core.admin.vo.CareContextRefVo careContext)
	{
	}

	@SuppressWarnings("unused")
	public void validateinsertMetricsRecord(ims.core.vo.VitalSignsVo vitalSignVo)
	{
	}

	@SuppressWarnings("unused")
	public void validatesaveAll(ims.coe.vo.MnaEnhancedVo mnaEnhancedRecordVo, ims.core.vo.CareContextShortVo careContext)
	{
	}

	@SuppressWarnings("unused")
	public void validateisPartOfEatingAssessment(ims.nursing.vo.MiniNutritionalAssessment mnaRecordVo)
	{
	}
}