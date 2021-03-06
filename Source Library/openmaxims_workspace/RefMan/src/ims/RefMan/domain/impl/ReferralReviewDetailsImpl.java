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
// This code was generated by Catalin Tomozei using IMS Development Environment (version 1.65 build 3218.18820)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.domain.impl;

import ims.RefMan.domain.base.impl.BaseReferralReviewDetailsImpl;
import ims.RefMan.domain.objects.CatsReferral;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.CatsReferralReviewDetailVo;
import ims.RefMan.vo.domain.CatsReferralReviewDetailVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;

public class ReferralReviewDetailsImpl extends BaseReferralReviewDetailsImpl
{
	private static final long serialVersionUID = 1L;
	
	public CatsReferralReviewDetailVo saveReferral(CatsReferralReviewDetailVo vo) throws StaleObjectException
	{
		if(vo == null)
			throw new DomainRuntimeException("CatsReferralVo is null");
		if (vo != null && !vo.isValidated())
			throw new DomainRuntimeException("CatsReferralVo is not validated");
		
		DomainFactory factory = getDomainFactory();
		CatsReferral doCats = CatsReferralReviewDetailVoAssembler.extractCatsReferral(factory, vo);
		factory.save(doCats);
		return CatsReferralReviewDetailVoAssembler.create(doCats);
	}
	
	public CatsReferralReviewDetailVo getCatsReferral(CatsReferralRefVo voRef) 
	{
		if(voRef == null)
			throw new DomainRuntimeException("Cannot get CatsReferralVo for null CatsReferralRefVo");	
		
		return CatsReferralReviewDetailVoAssembler.create((CatsReferral)getDomainFactory().getDomainObject(CatsReferral.class, voRef.getID_CatsReferral()));
	}

}
