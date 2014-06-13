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
// This code was generated by Charlotte Murphy using IMS Development Environment (version 1.45 build 2348.20080)
// Copyright (C) 1995-2006 IMS MAXIMS plc. All rights reserved.

package ims.admin.domain.impl;

import ims.admin.domain.OrganisationAndLocation;
import ims.admin.domain.base.impl.BaseClinicAdminImpl;
import ims.core.resource.place.domain.objects.Clinic;
import ims.core.resource.place.vo.ClinicRefVo;
import ims.core.resource.place.vo.ClinicRefVoCollection;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.ClinicCodeVoCollection;
import ims.core.vo.ClinicLiteVoCollection;
import ims.core.vo.ClinicVo;
import ims.core.vo.ClinicVoCollection;
import ims.core.vo.LocShortMappingsVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.domain.ClinicCodeVoAssembler;
import ims.core.vo.domain.ClinicLiteVoAssembler;
import ims.core.vo.domain.ClinicVoAssembler;
import ims.core.vo.domain.LocationLiteVoAssembler;
import ims.core.vo.lookups.TaxonomyType;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.domain.exceptions.UnqViolationUncheckedException;
import ims.framework.exceptions.CodingRuntimeException;

import java.util.ArrayList;
import java.util.List;

public class ClinicAdminImpl extends BaseClinicAdminImpl
{
	private static final long serialVersionUID = 1L;
	
	public ClinicVo saveClinic(ClinicVo voClinic) throws StaleObjectException, UniqueKeyViolationException
	{
		if(voClinic == null)
			throw new CodingRuntimeException("This Clinic Value Object is null");
		if(!voClinic.isValidated())
			throw new DomainRuntimeException("This Clinic Value Object has not been validated");
		
		DomainFactory factory = getDomainFactory();		
		Clinic domClinic = ClinicVoAssembler.extractClinic(factory,voClinic);
		domClinic.setUpperNames();
		
		try
		{
			factory.save(domClinic);
		}
		catch (UnqViolationUncheckedException e)
		{	
			throw new UniqueKeyViolationException("This Clinic Code already exists. ", e);
		}
		return ClinicVoAssembler.create(domClinic);
	}

	public LocationLiteVoCollection listActiveHospitalsLite()
	{
		OrganisationAndLocation orgLoc = (OrganisationAndLocation)getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.listActiveHospitalsLite();
	}
	
	public ClinicLiteVoCollection listClinicsforLocation(LocationRefVo voLocationRef)
	{
		if(voLocationRef == null)
			throw new CodingRuntimeException("This Location Object is null");
		
		return listClinicsForLocationByClinicName(voLocationRef, null);
	}

	public ClinicLiteVoCollection listClinicsByName(String nameFilter)
	{
		if(nameFilter == null)
			throw new CodingRuntimeException("This Name Filter in listClinicsByName cannot be null");

		return listClinicsForLocationByClinicName(null, nameFilter);
	}

	public ClinicLiteVoCollection listClinicsForLocationByClinicName(LocationRefVo refLocationVo, String strClinicName)
	{
		ArrayList names = new ArrayList();
		ArrayList values = new ArrayList();
		String prepend = " where ";
		DomainFactory factory = getDomainFactory();
		
		StringBuffer hql = new StringBuffer("from Clinic clin ");
		if (refLocationVo != null)
		{
			hql.append(prepend + " clin.clinicLocation.id = :idLocation ");
			names.add("idLocation");
			values.add(refLocationVo.getID_Location());
			prepend = " and ";
		}		
		if (strClinicName != null && strClinicName.length() > 0)
		{
			hql.append(prepend + " (upper(clin.clinicName) like :clinName) ");
			names.add("clinName");
			values.add("%" + strClinicName.toUpperCase() + "%");
		}
		List clinics = factory.find(hql.toString(), names, values, 1000);
		return ClinicLiteVoAssembler.createClinicLiteVoCollectionFromClinic(clinics).sort(true);
	}

	public ClinicVo getClinic(ClinicRefVo clinicRef)
	{
		return ClinicVoAssembler.create((Clinic)this.getDomainFactory().getDomainObject(clinicRef));
	}
	
	// wdev-7921
	// Get the Clinic associated with the given code with PAS mapping
	public ClinicVo getClinicByPASCode(String clinicCode)
	{
		ArrayList names = new ArrayList();
		ArrayList values = new ArrayList();
		String prepend = " where ";
		DomainFactory factory = getDomainFactory();
		
		StringBuffer hql = new StringBuffer("from Clinic clin join clin.codeMappings map where map.taxonomyCode = :clinicCode and map.taxonomyName = :type");
		names.add("clinicCode");
		values.add(clinicCode);
		names.add("type");
		values.add(getDomLookup(TaxonomyType.PAS));
		List clinics = factory.find(hql.toString(), names, values, 1000);
		if (clinics != null && clinics.size() > 0)
			return ClinicVoAssembler.create((Clinic) clinics.get(0));
		
		return null;
	}

	public LocationLiteVoCollection listLocationsWithClinics()
	{
		List l = getDomainFactory().find("select distinct clinicLocation from Clinic");
		return LocationLiteVoAssembler.createLocationLiteVoCollectionFromLocation(l);
	}

	public LocShortMappingsVo getLocShortMappingsByRef(LocationRefVo locRef)
	{
		OrganisationAndLocation orgLoc = (OrganisationAndLocation)getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.getLocation(locRef.getID_Location());
	}

	public ClinicCodeVoCollection listClinicsCodes(ClinicRefVoCollection voRefCollClinics) 
	{
		if (voRefCollClinics == null)
			return null;
		if (voRefCollClinics.size() <= 0)
			return null;

		DomainFactory factory = getDomainFactory();
		StringBuffer hqlStart = new StringBuffer();
		String hql;

		hqlStart.append("from Clinic v where c.id in ( ");
		for (int i = 0; i < voRefCollClinics.size(); i++)
		{
			if (i == 0 || i == voRefCollClinics.size())
				hqlStart.append(voRefCollClinics.get(i).getID_Clinic());
			else
				hqlStart.append("," + voRefCollClinics.get(i).getID_Clinic());
		}

		hql = hqlStart.toString() + " )";
		List lst = factory.find(hql);
		return ClinicCodeVoAssembler.createClinicCodeVoCollectionFromClinic(lst);
	}

	public ClinicVoCollection listClinicsByNameCodeLocation(LocationRefVo voLocRef, LocationRefVo outpatientDepartment, String strClinicName, String strClinicCode, Boolean bActiveOnly)
	{
		if(voLocRef == null && strClinicName == null && strClinicCode == null)
			throw new CodingRuntimeException("No parameters set for listClinicsByNameCodeLocation method");
		
		ArrayList names = new ArrayList();
		ArrayList values = new ArrayList();
		String prepend = " where ";
		DomainFactory factory = getDomainFactory();
		
		StringBuffer hql = new StringBuffer("from Clinic clin left join clin.codeMappings as codeMappings ");
		if (voLocRef != null)
		{
			hql.append(prepend + " clin.clinicLocation.id = :idLocation ");
			names.add("idLocation");
			values.add(voLocRef.getID_Location());
			prepend = " and ";
		}
		
		//WDEV-11914 - start here
		if (outpatientDepartment != null && outpatientDepartment.getID_LocationIsNotNull())
		{
			hql.append(prepend + " clin.outpatientDept.id = :idOutpatientDept ");
			names.add("idOutpatientDept");
			values.add(outpatientDepartment.getID_Location());
			prepend = " and ";
		}
		//WDEV-11914 - ends here
		
		//upperName max 20
		if(strClinicName != null && strClinicName.length()>19)
			strClinicName = strClinicName.substring(0,20);
		
		if (strClinicName != null && strClinicName.length() > 0)
		{
			hql.append(prepend +  " clin.upperName like :ClinicName");
			names.add("ClinicName");
			values.add(strClinicName.toUpperCase() + "%");
			prepend = " and ";
		}
		if (strClinicCode != null && strClinicCode.length() > 0)
		{
			hql.append(prepend +  " codeMappings.taxonomyCode like :taxonomyCode");
			names.add("taxonomyCode");
			values.add(strClinicCode + "%");
			prepend = " and ";
		}
		if (bActiveOnly != null && bActiveOnly)
		{
			hql.append(prepend + " clin.isActive like :Active");
			names.add("Active");
			values.add(Boolean.TRUE);
		}
		
		hql.append(" order by clin.upperName asc"); //WDEV-15426
		
		List clinics = factory.find(hql.toString(), names, values, 1000);
		return ClinicVoAssembler.createClinicVoCollectionFromClinic(clinics);
	}

	//WDEV-11914
	public LocationLiteVoCollection listActiveOutpatientDepartment(LocationRefVo hospital) 
	{
		if(hospital == null || !hospital.getID_LocationIsNotNull())
			throw new CodingRuntimeException("Can not list Outpatient Departments on null Hospital Id.");
		
		OrganisationAndLocation orgLoc = (OrganisationAndLocation)getDomainImpl(OrganisationAndLocationImpl.class);
		return orgLoc.listActiveOutpatDeptsForHospitalByNameLite(hospital, null);
	}

	//WDEV-12511
	public ClinicLiteVoCollection listClinicsForLocationByClinicName(LocationRefVo refLocationVo, String strClinicName,	Boolean activeOnly) 
	{
		ArrayList names = new ArrayList();
		ArrayList values = new ArrayList();
		String prepend = " where ";
		DomainFactory factory = getDomainFactory();
		
		StringBuffer hql = new StringBuffer("from Clinic clin ");
		if (refLocationVo != null)
		{
			hql.append(prepend + " clin.clinicLocation.id = :idLocation ");
			names.add("idLocation");
			values.add(refLocationVo.getID_Location());
			prepend = " and ";
		}		
		if (strClinicName != null && strClinicName.length() > 0)
		{
			hql.append(prepend + " (upper(clin.clinicName) like :clinName) ");
			names.add("clinName");
			values.add("%" + strClinicName.toUpperCase() + "%");
		}
		if (activeOnly != null && activeOnly)
		{
			hql.append(prepend + " clin.isActive like :Active");
			names.add("Active");
			values.add(Boolean.TRUE);
		}
		List clinics = factory.find(hql.toString(), names, values, 1000);
		return ClinicLiteVoAssembler.createClinicLiteVoCollectionFromClinic(clinics).sort(true);
	}
}