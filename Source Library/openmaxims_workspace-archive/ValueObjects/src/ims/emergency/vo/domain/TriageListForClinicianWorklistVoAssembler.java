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
/*
 * This code was generated
 * Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.
 * IMS Development Environment (version 1.80 build 5007.25751)
 * WARNING: DO NOT MODIFY the content of this file
 * Generated on 16/04/2014, 12:31
 *
 */
package ims.emergency.vo.domain;

import ims.vo.domain.DomainObjectMap;
import java.util.HashMap;

import org.hibernate.proxy.HibernateProxy;

/**
 * @author Cristian Belciug
 */
public class TriageListForClinicianWorklistVoAssembler
{
  	/**
	 * Copy one ValueObject to another
	 * @param valueObjectDest to be updated
	 * @param valueObjectSrc to copy values from
	 */
	 public static ims.emergency.vo.TriageListForClinicianWorklistVo copy(ims.emergency.vo.TriageListForClinicianWorklistVo valueObjectDest, ims.emergency.vo.TriageListForClinicianWorklistVo valueObjectSrc) 
	 {
	 	if (null == valueObjectSrc) 
		{
			return valueObjectSrc;
		}
		valueObjectDest.setID_Triage(valueObjectSrc.getID_Triage());
	    valueObjectDest.setIsRIE(valueObjectSrc.getIsRIE());
		// CurrentTriagePriority
		valueObjectDest.setCurrentTriagePriority(valueObjectSrc.getCurrentTriagePriority());
		// MainPresentingProblem
		valueObjectDest.setMainPresentingProblem(valueObjectSrc.getMainPresentingProblem());
		// TriageStartDateTime
		valueObjectDest.setTriageStartDateTime(valueObjectSrc.getTriageStartDateTime());
		// MedicInterventionStartDateTime
		valueObjectDest.setMedicInterventionStartDateTime(valueObjectSrc.getMedicInterventionStartDateTime());
	 	return valueObjectDest;
	 }

 
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * This is a convenience method only.
	 * It is intended to be used when one called to an Assembler is made.
 	 * If more than one call to an Assembler is made then #createTriageListForClinicianWorklistVoCollectionFromTriage(DomainObjectMap, Set) should be used.
	 * @param domainObjectSet - Set of ims.emergency.domain.objects.Triage objects.
	 */
	public static ims.emergency.vo.TriageListForClinicianWorklistVoCollection createTriageListForClinicianWorklistVoCollectionFromTriage(java.util.Set domainObjectSet)	
	{
		return createTriageListForClinicianWorklistVoCollectionFromTriage(new DomainObjectMap(), domainObjectSet);
	}
	
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectSet - Set of ims.emergency.domain.objects.Triage objects.
	 */
	public static ims.emergency.vo.TriageListForClinicianWorklistVoCollection createTriageListForClinicianWorklistVoCollectionFromTriage(DomainObjectMap map, java.util.Set domainObjectSet)	
	{
		ims.emergency.vo.TriageListForClinicianWorklistVoCollection voList = new ims.emergency.vo.TriageListForClinicianWorklistVoCollection();
		if ( null == domainObjectSet ) 
		{
			return voList;
		}
		int rieCount=0;
		int activeCount=0;
		java.util.Iterator iterator = domainObjectSet.iterator();
		while( iterator.hasNext() ) 
		{
			ims.emergency.domain.objects.Triage domainObject = (ims.emergency.domain.objects.Triage) iterator.next();
			ims.emergency.vo.TriageListForClinicianWorklistVo vo = create(map, domainObject);
			
			if (vo != null)
				voList.add(vo);
				
			if (domainObject != null)
			{				
				if (domainObject.getIsRIE() != null && domainObject.getIsRIE().booleanValue() == true)
					rieCount++;
				else
					activeCount++;
			}
		}
		voList.setRieCount(rieCount);
		voList.setActiveCount(activeCount);
		return voList;
	}

	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param domainObjectList - List of ims.emergency.domain.objects.Triage objects.
	 */
	public static ims.emergency.vo.TriageListForClinicianWorklistVoCollection createTriageListForClinicianWorklistVoCollectionFromTriage(java.util.List domainObjectList) 
	{
		return createTriageListForClinicianWorklistVoCollectionFromTriage(new DomainObjectMap(), domainObjectList);
	}
	
	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectList - List of ims.emergency.domain.objects.Triage objects.
	 */
	public static ims.emergency.vo.TriageListForClinicianWorklistVoCollection createTriageListForClinicianWorklistVoCollectionFromTriage(DomainObjectMap map, java.util.List domainObjectList) 
	{
		ims.emergency.vo.TriageListForClinicianWorklistVoCollection voList = new ims.emergency.vo.TriageListForClinicianWorklistVoCollection();
		if ( null == domainObjectList ) 
		{
			return voList;
		}		
		int rieCount=0;
		int activeCount=0;
		for (int i = 0; i < domainObjectList.size(); i++)
		{
			ims.emergency.domain.objects.Triage domainObject = (ims.emergency.domain.objects.Triage) domainObjectList.get(i);
			ims.emergency.vo.TriageListForClinicianWorklistVo vo = create(map, domainObject);

			if (vo != null)
				voList.add(vo);
			
			if (domainObject != null)
			{
				if (domainObject.getIsRIE() != null && domainObject.getIsRIE().booleanValue() == true)
					rieCount++;
				else
					activeCount++;
			}
		}
		
		voList.setRieCount(rieCount);
		voList.setActiveCount(activeCount);
		return voList;
	}

	/**
	 * Create the ims.emergency.domain.objects.Triage set from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.Set extractTriageSet(ims.domain.ILightweightDomainFactory domainFactory, ims.emergency.vo.TriageListForClinicianWorklistVoCollection voCollection) 
	 {
	 	return extractTriageSet(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.Set extractTriageSet(ims.domain.ILightweightDomainFactory domainFactory, ims.emergency.vo.TriageListForClinicianWorklistVoCollection voCollection, java.util.Set domainObjectSet, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectSet == null)
		{
			domainObjectSet = new java.util.HashSet();			
		}
		java.util.Set newSet = new java.util.HashSet();
		for(int i=0; i<size; i++) 
		{
			ims.emergency.vo.TriageListForClinicianWorklistVo vo = voCollection.get(i);
			ims.emergency.domain.objects.Triage domainObject = TriageListForClinicianWorklistVoAssembler.extractTriage(domainFactory, vo, domMap);

			//TODO: This can only occur in the situation of a stale object exception. For now leave it to the Interceptor to handle it.
			if (domainObject == null)
			{
				continue;
			}
			
			//Trying to avoid the hibernate collection being marked as dirty via its public interface methods. (like add)
			if (!domainObjectSet.contains(domainObject)) domainObjectSet.add(domainObject);
			newSet.add(domainObject);			
		}
		java.util.Set removedSet = new java.util.HashSet();
		java.util.Iterator iter = domainObjectSet.iterator();
		//Find out which objects need to be removed
		while (iter.hasNext())
		{
			ims.domain.DomainObject o = (ims.domain.DomainObject)iter.next();			
			if ((o == null || o.getIsRIE() == null || !o.getIsRIE().booleanValue()) && !newSet.contains(o))
			{
				removedSet.add(o);
			}
		}
		iter = removedSet.iterator();
		//Remove the unwanted objects
		while (iter.hasNext())
		{
			domainObjectSet.remove(iter.next());
		}
		return domainObjectSet;	 
	 }


	/**
	 * Create the ims.emergency.domain.objects.Triage list from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.List extractTriageList(ims.domain.ILightweightDomainFactory domainFactory, ims.emergency.vo.TriageListForClinicianWorklistVoCollection voCollection) 
	 {
	 	return extractTriageList(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.List extractTriageList(ims.domain.ILightweightDomainFactory domainFactory, ims.emergency.vo.TriageListForClinicianWorklistVoCollection voCollection, java.util.List domainObjectList, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectList == null)
		{
			domainObjectList = new java.util.ArrayList();			
		}
		for(int i=0; i<size; i++) 
		{
			ims.emergency.vo.TriageListForClinicianWorklistVo vo = voCollection.get(i);
			ims.emergency.domain.objects.Triage domainObject = TriageListForClinicianWorklistVoAssembler.extractTriage(domainFactory, vo, domMap);

			//TODO: This can only occur in the situation of a stale object exception. For now leave it to the Interceptor to handle it.
			if (domainObject == null)
			{
				continue;
			}

			int domIdx = domainObjectList.indexOf(domainObject);
			if (domIdx == -1)
			{
				domainObjectList.add(i, domainObject);
			}
			else if (i != domIdx && i < domainObjectList.size())
			{
				Object tmp = domainObjectList.get(i);
				domainObjectList.set(i, domainObjectList.get(domIdx));
				domainObjectList.set(domIdx, tmp);
			}
		}
		
		//Remove all ones in domList where index > voCollection.size() as these should
		//now represent the ones removed from the VO collection. No longer referenced.
		int i1=domainObjectList.size();
		while (i1 > size)
		{
			domainObjectList.remove(i1-1);
			i1=domainObjectList.size();
		}
		return domainObjectList;	 
	 }

 

	/**
	 * Create the ValueObject from the ims.emergency.domain.objects.Triage object.
	 * @param domainObject ims.emergency.domain.objects.Triage
	 */
	 public static ims.emergency.vo.TriageListForClinicianWorklistVo create(ims.emergency.domain.objects.Triage domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return null;
		}
		DomainObjectMap map = new DomainObjectMap();
		return create(map, domainObject);
	 }
	 
	 /**
	  * Create the ValueObject from the ims.emergency.domain.objects.Triage object.
	  * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	  * @param domainObject
	  */
	  public static ims.emergency.vo.TriageListForClinicianWorklistVo create(DomainObjectMap map, ims.emergency.domain.objects.Triage domainObject) 
	  {
	  		if (null == domainObject) 
	  		{
				return null;
			}
			// check if the domainObject already has a valueObject created for it
			ims.emergency.vo.TriageListForClinicianWorklistVo valueObject = (ims.emergency.vo.TriageListForClinicianWorklistVo) map.getValueObject(domainObject, ims.emergency.vo.TriageListForClinicianWorklistVo.class);
			if ( null == valueObject ) 
			{
				valueObject = new ims.emergency.vo.TriageListForClinicianWorklistVo(domainObject.getId(), domainObject.getVersion());
				map.addValueObject(domainObject, valueObject);

				valueObject = insert(map, valueObject, domainObject);
				
			}
	 		return valueObject;
	  }

	/**
	 * Update the ValueObject with the Domain Object.
	 * @param valueObject to be updated
	 * @param domainObject ims.emergency.domain.objects.Triage
	 */
	 public static ims.emergency.vo.TriageListForClinicianWorklistVo insert(ims.emergency.vo.TriageListForClinicianWorklistVo valueObject, ims.emergency.domain.objects.Triage domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
		DomainObjectMap map = new DomainObjectMap();
		return insert(map, valueObject, domainObject);
	 }
	 
	/**
	 * Update the ValueObject with the Domain Object.
	 * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	 * @param valueObject to be updated
	 * @param domainObject ims.emergency.domain.objects.Triage
	 */
	 public static ims.emergency.vo.TriageListForClinicianWorklistVo insert(DomainObjectMap map, ims.emergency.vo.TriageListForClinicianWorklistVo valueObject, ims.emergency.domain.objects.Triage domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
	 	if (null == map) 
	 	{
			map = new DomainObjectMap();
		}

		valueObject.setID_Triage(domainObject.getId());
		valueObject.setIsRIE(domainObject.getIsRIE());
		
		// If this is a recordedInError record, and the domainObject
		// value isIncludeRecord has not been set, then we return null and
		// not the value object
		if (valueObject.getIsRIE() != null && valueObject.getIsRIE().booleanValue() == true && !domainObject.isIncludeRecord())
			return null;
			
		// If this is not a recordedInError record, and the domainObject
		// value isIncludeRecord has been set, then we return null and
		// not the value object
		if ((valueObject.getIsRIE() == null || valueObject.getIsRIE().booleanValue() == false) && domainObject.isIncludeRecord())
			return null;
			
		// CurrentTriagePriority
		ims.domain.lookups.LookupInstance instance1 = domainObject.getCurrentTriagePriority();
		if ( null != instance1 ) {
			ims.framework.utils.ImagePath img = null;
			ims.framework.utils.Color color = null;		
			img = null;
			if (instance1.getImage() != null) 
			{
				img = new ims.framework.utils.ImagePath(instance1.getImage().getImageId(), instance1.getImage().getImagePath());
			}
			color = instance1.getColor();
			if (color != null) 
				color.getValue();

			ims.emergency.vo.lookups.TriagePriority voLookup1 = new ims.emergency.vo.lookups.TriagePriority(instance1.getId(),instance1.getText(), instance1.isActive(), null, img, color);
			ims.emergency.vo.lookups.TriagePriority parentVoLookup1 = voLookup1;
			ims.domain.lookups.LookupInstance parent1 = instance1.getParent();
			while (parent1 != null)
			{
				if (parent1.getImage() != null) 
				{
					img = new ims.framework.utils.ImagePath(parent1.getImage().getImageId(), parent1.getImage().getImagePath() );
				}
				else 
				{
					img = null;
				}
				color = parent1.getColor();
    			if (color != null) 
    				color.getValue();
								parentVoLookup1.setParent(new ims.emergency.vo.lookups.TriagePriority(parent1.getId(),parent1.getText(), parent1.isActive(), null, img, color));
				parentVoLookup1 = parentVoLookup1.getParent();
								parent1 = parent1.getParent();
			}			
			valueObject.setCurrentTriagePriority(voLookup1);
		}
				// MainPresentingProblem
		valueObject.setMainPresentingProblem(ims.emergency.vo.domain.PatientProblemForClinicianWorklistVoAssembler.create(map, domainObject.getMainPresentingProblem()) );
		// TriageStartDateTime
		java.util.Date TriageStartDateTime = domainObject.getTriageStartDateTime();
		if ( null != TriageStartDateTime ) 
		{
			valueObject.setTriageStartDateTime(new ims.framework.utils.DateTime(TriageStartDateTime) );
		}
		// MedicInterventionStartDateTime
		java.util.Date MedicInterventionStartDateTime = domainObject.getMedicInterventionStartDateTime();
		if ( null != MedicInterventionStartDateTime ) 
		{
			valueObject.setMedicInterventionStartDateTime(new ims.framework.utils.DateTime(MedicInterventionStartDateTime) );
		}
 		return valueObject;
	 }


	/**
	 * Create the domain object from the value object.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param valueObject - extract the domain object fields from this.
	 */
	public static ims.emergency.domain.objects.Triage extractTriage(ims.domain.ILightweightDomainFactory domainFactory, ims.emergency.vo.TriageListForClinicianWorklistVo valueObject) 
	{
		return 	extractTriage(domainFactory, valueObject, new HashMap());
	}

	public static ims.emergency.domain.objects.Triage extractTriage(ims.domain.ILightweightDomainFactory domainFactory, ims.emergency.vo.TriageListForClinicianWorklistVo valueObject, HashMap domMap) 
	{
		if (null == valueObject) 
		{
			return null;
		}
		Integer id = valueObject.getID_Triage();
		ims.emergency.domain.objects.Triage domainObject = null;
		if ( null == id) 
		{
			if (domMap.get(valueObject) != null)
			{
				return (ims.emergency.domain.objects.Triage)domMap.get(valueObject);
			}
			// ims.emergency.vo.TriageListForClinicianWorklistVo ID_Triage field is unknown
			domainObject = new ims.emergency.domain.objects.Triage();
			domMap.put(valueObject, domainObject);
		}
		else 
		{
			String key = (valueObject.getClass().getName() + "__" + valueObject.getID_Triage());
			if (domMap.get(key) != null)
			{
				return (ims.emergency.domain.objects.Triage)domMap.get(key);
			}
			domainObject = (ims.emergency.domain.objects.Triage) domainFactory.getDomainObject(ims.emergency.domain.objects.Triage.class, id );
			
			//TODO: Not sure how this should be handled. Effectively it must be a staleobject exception, but maybe should be handled as that further up.
			if (domainObject == null) 
				return null;

			domMap.put(key, domainObject);
		}
		domainObject.setVersion(valueObject.getVersion_Triage());

		// create LookupInstance from vo LookupType
		ims.domain.lookups.LookupInstance value1 = null;
		if ( null != valueObject.getCurrentTriagePriority() ) 
		{
			value1 =
				domainFactory.getLookupInstance(valueObject.getCurrentTriagePriority().getID());
		}
		domainObject.setCurrentTriagePriority(value1);
	// SaveAsRefVO - treated as a refVo in extract methods
	ims.core.clinical.domain.objects.PatientProblem value2 = null;
		if ( null != valueObject.getMainPresentingProblem() ) 
		{
			if (valueObject.getMainPresentingProblem().getBoId() == null)
			{
				if (domMap.get(valueObject.getMainPresentingProblem()) != null)
				{
					value2 = (ims.core.clinical.domain.objects.PatientProblem)domMap.get(valueObject.getMainPresentingProblem());
				}
			}
			else
			{
				value2 = (ims.core.clinical.domain.objects.PatientProblem)domainFactory.getDomainObject(ims.core.clinical.domain.objects.PatientProblem.class, valueObject.getMainPresentingProblem().getBoId());
			}
		}
		domainObject.setMainPresentingProblem(value2);
		ims.framework.utils.DateTime dateTime3 = valueObject.getTriageStartDateTime();
		java.util.Date value3 = null;
		if ( dateTime3 != null ) 
		{
			value3 = dateTime3.getJavaDate();
		}
		domainObject.setTriageStartDateTime(value3);
		ims.framework.utils.DateTime dateTime4 = valueObject.getMedicInterventionStartDateTime();
		java.util.Date value4 = null;
		if ( dateTime4 != null ) 
		{
			value4 = dateTime4.getJavaDate();
		}
		domainObject.setMedicInterventionStartDateTime(value4);

		return domainObject;
	}

}
