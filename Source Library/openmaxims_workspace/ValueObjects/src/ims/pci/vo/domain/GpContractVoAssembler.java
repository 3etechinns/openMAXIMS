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
/*
 * This code was generated
 * Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.
 * IMS Development Environment (version 1.80 build 5589.25814)
 * WARNING: DO NOT MODIFY the content of this file
 * Generated on 12/10/2015, 13:25
 *
 */
package ims.pci.vo.domain;

import ims.vo.domain.DomainObjectMap;
import java.util.HashMap;

import org.hibernate.proxy.HibernateProxy;

/**
 * @author Cristian Belciug
 */
public class GpContractVoAssembler
{
  	/**
	 * Copy one ValueObject to another
	 * @param valueObjectDest to be updated
	 * @param valueObjectSrc to copy values from
	 */
	 public static ims.pci.vo.GpContractVo copy(ims.pci.vo.GpContractVo valueObjectDest, ims.pci.vo.GpContractVo valueObjectSrc) 
	 {
	 	if (null == valueObjectSrc) 
		{
			return valueObjectSrc;
		}
		valueObjectDest.setID_GpContract(valueObjectSrc.getID_GpContract());
	    valueObjectDest.setIsRIE(valueObjectSrc.getIsRIE());
		// GP
		valueObjectDest.setGP(valueObjectSrc.getGP());
		// ContractId
		valueObjectDest.setContractId(valueObjectSrc.getContractId());
		// ContractStartDate
		valueObjectDest.setContractStartDate(valueObjectSrc.getContractStartDate());
		// ContractEndDate
		valueObjectDest.setContractEndDate(valueObjectSrc.getContractEndDate());
	 	return valueObjectDest;
	 }

 
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * This is a convenience method only.
	 * It is intended to be used when one called to an Assembler is made.
 	 * If more than one call to an Assembler is made then #createGpContractVoCollectionFromGpContract(DomainObjectMap, Set) should be used.
	 * @param domainObjectSet - Set of ims.pci.domain.objects.GpContract objects.
	 */
	public static ims.pci.vo.GpContractVoCollection createGpContractVoCollectionFromGpContract(java.util.Set domainObjectSet)	
	{
		return createGpContractVoCollectionFromGpContract(new DomainObjectMap(), domainObjectSet);
	}
	
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectSet - Set of ims.pci.domain.objects.GpContract objects.
	 */
	public static ims.pci.vo.GpContractVoCollection createGpContractVoCollectionFromGpContract(DomainObjectMap map, java.util.Set domainObjectSet)	
	{
		ims.pci.vo.GpContractVoCollection voList = new ims.pci.vo.GpContractVoCollection();
		if ( null == domainObjectSet ) 
		{
			return voList;
		}
		int rieCount=0;
		int activeCount=0;
		java.util.Iterator iterator = domainObjectSet.iterator();
		while( iterator.hasNext() ) 
		{
			ims.pci.domain.objects.GpContract domainObject = (ims.pci.domain.objects.GpContract) iterator.next();
			ims.pci.vo.GpContractVo vo = create(map, domainObject);
			
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
	 * @param domainObjectList - List of ims.pci.domain.objects.GpContract objects.
	 */
	public static ims.pci.vo.GpContractVoCollection createGpContractVoCollectionFromGpContract(java.util.List domainObjectList) 
	{
		return createGpContractVoCollectionFromGpContract(new DomainObjectMap(), domainObjectList);
	}
	
	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectList - List of ims.pci.domain.objects.GpContract objects.
	 */
	public static ims.pci.vo.GpContractVoCollection createGpContractVoCollectionFromGpContract(DomainObjectMap map, java.util.List domainObjectList) 
	{
		ims.pci.vo.GpContractVoCollection voList = new ims.pci.vo.GpContractVoCollection();
		if ( null == domainObjectList ) 
		{
			return voList;
		}		
		int rieCount=0;
		int activeCount=0;
		for (int i = 0; i < domainObjectList.size(); i++)
		{
			ims.pci.domain.objects.GpContract domainObject = (ims.pci.domain.objects.GpContract) domainObjectList.get(i);
			ims.pci.vo.GpContractVo vo = create(map, domainObject);

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
	 * Create the ims.pci.domain.objects.GpContract set from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.Set extractGpContractSet(ims.domain.ILightweightDomainFactory domainFactory, ims.pci.vo.GpContractVoCollection voCollection) 
	 {
	 	return extractGpContractSet(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.Set extractGpContractSet(ims.domain.ILightweightDomainFactory domainFactory, ims.pci.vo.GpContractVoCollection voCollection, java.util.Set domainObjectSet, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectSet == null)
		{
			domainObjectSet = new java.util.HashSet();			
		}
		java.util.Set newSet = new java.util.HashSet();
		for(int i=0; i<size; i++) 
		{
			ims.pci.vo.GpContractVo vo = voCollection.get(i);
			ims.pci.domain.objects.GpContract domainObject = GpContractVoAssembler.extractGpContract(domainFactory, vo, domMap);

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
	 * Create the ims.pci.domain.objects.GpContract list from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.List extractGpContractList(ims.domain.ILightweightDomainFactory domainFactory, ims.pci.vo.GpContractVoCollection voCollection) 
	 {
	 	return extractGpContractList(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.List extractGpContractList(ims.domain.ILightweightDomainFactory domainFactory, ims.pci.vo.GpContractVoCollection voCollection, java.util.List domainObjectList, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectList == null)
		{
			domainObjectList = new java.util.ArrayList();			
		}
		for(int i=0; i<size; i++) 
		{
			ims.pci.vo.GpContractVo vo = voCollection.get(i);
			ims.pci.domain.objects.GpContract domainObject = GpContractVoAssembler.extractGpContract(domainFactory, vo, domMap);

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
	 * Create the ValueObject from the ims.pci.domain.objects.GpContract object.
	 * @param domainObject ims.pci.domain.objects.GpContract
	 */
	 public static ims.pci.vo.GpContractVo create(ims.pci.domain.objects.GpContract domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return null;
		}
		DomainObjectMap map = new DomainObjectMap();
		return create(map, domainObject);
	 }
	 
	 /**
	  * Create the ValueObject from the ims.pci.domain.objects.GpContract object.
	  * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	  * @param domainObject
	  */
	  public static ims.pci.vo.GpContractVo create(DomainObjectMap map, ims.pci.domain.objects.GpContract domainObject) 
	  {
	  		if (null == domainObject) 
	  		{
				return null;
			}
			// check if the domainObject already has a valueObject created for it
			ims.pci.vo.GpContractVo valueObject = (ims.pci.vo.GpContractVo) map.getValueObject(domainObject, ims.pci.vo.GpContractVo.class);
			if ( null == valueObject ) 
			{
				valueObject = new ims.pci.vo.GpContractVo(domainObject.getId(), domainObject.getVersion());
				map.addValueObject(domainObject, valueObject);

				valueObject = insert(map, valueObject, domainObject);
				
			}
	 		return valueObject;
	  }

	/**
	 * Update the ValueObject with the Domain Object.
	 * @param valueObject to be updated
	 * @param domainObject ims.pci.domain.objects.GpContract
	 */
	 public static ims.pci.vo.GpContractVo insert(ims.pci.vo.GpContractVo valueObject, ims.pci.domain.objects.GpContract domainObject) 
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
	 * @param domainObject ims.pci.domain.objects.GpContract
	 */
	 public static ims.pci.vo.GpContractVo insert(DomainObjectMap map, ims.pci.vo.GpContractVo valueObject, ims.pci.domain.objects.GpContract domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
	 	if (null == map) 
	 	{
			map = new DomainObjectMap();
		}

		valueObject.setID_GpContract(domainObject.getId());
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
			
		// GP
		valueObject.setGP(ims.core.vo.domain.GpLiteWithNameVoAssembler.create(map, domainObject.getGP()) );
		// ContractId
		valueObject.setContractId(domainObject.getContractId());
		// ContractStartDate
		java.util.Date ContractStartDate = domainObject.getContractStartDate();
		if ( null != ContractStartDate ) 
		{
			valueObject.setContractStartDate(new ims.framework.utils.Date(ContractStartDate) );
		}
		// ContractEndDate
		java.util.Date ContractEndDate = domainObject.getContractEndDate();
		if ( null != ContractEndDate ) 
		{
			valueObject.setContractEndDate(new ims.framework.utils.Date(ContractEndDate) );
		}
 		return valueObject;
	 }


	/**
	 * Create the domain object from the value object.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param valueObject - extract the domain object fields from this.
	 */
	public static ims.pci.domain.objects.GpContract extractGpContract(ims.domain.ILightweightDomainFactory domainFactory, ims.pci.vo.GpContractVo valueObject) 
	{
		return 	extractGpContract(domainFactory, valueObject, new HashMap());
	}

	public static ims.pci.domain.objects.GpContract extractGpContract(ims.domain.ILightweightDomainFactory domainFactory, ims.pci.vo.GpContractVo valueObject, HashMap domMap) 
	{
		if (null == valueObject) 
		{
			return null;
		}
		Integer id = valueObject.getID_GpContract();
		ims.pci.domain.objects.GpContract domainObject = null;
		if ( null == id) 
		{
			if (domMap.get(valueObject) != null)
			{
				return (ims.pci.domain.objects.GpContract)domMap.get(valueObject);
			}
			// ims.pci.vo.GpContractVo ID_GpContract field is unknown
			domainObject = new ims.pci.domain.objects.GpContract();
			domMap.put(valueObject, domainObject);
		}
		else 
		{
			String key = (valueObject.getClass().getName() + "__" + valueObject.getID_GpContract());
			if (domMap.get(key) != null)
			{
				return (ims.pci.domain.objects.GpContract)domMap.get(key);
			}
			domainObject = (ims.pci.domain.objects.GpContract) domainFactory.getDomainObject(ims.pci.domain.objects.GpContract.class, id );
			
			//TODO: Not sure how this should be handled. Effectively it must be a staleobject exception, but maybe should be handled as that further up.
			if (domainObject == null) 
				return null;

			domMap.put(key, domainObject);
		}
		domainObject.setVersion(valueObject.getVersion_GpContract());

	// SaveAsRefVO - treated as a refVo in extract methods
	ims.core.resource.people.domain.objects.Gp value1 = null;
		if ( null != valueObject.getGP() ) 
		{
			if (valueObject.getGP().getBoId() == null)
			{
				if (domMap.get(valueObject.getGP()) != null)
				{
					value1 = (ims.core.resource.people.domain.objects.Gp)domMap.get(valueObject.getGP());
				}
			}
			else
			{
				value1 = (ims.core.resource.people.domain.objects.Gp)domainFactory.getDomainObject(ims.core.resource.people.domain.objects.Gp.class, valueObject.getGP().getBoId());
			}
		}
		domainObject.setGP(value1);
		//This is to overcome a bug in both Sybase and Oracle which prevents them from storing an empty string correctly
		//Sybase stores it as a single space, Oracle stores it as NULL. This fix will make them consistent at least.
		if (valueObject.getContractId() != null && valueObject.getContractId().equals(""))
		{
			valueObject.setContractId(null);
		}
		domainObject.setContractId(valueObject.getContractId());
		java.util.Date value3 = null;
		ims.framework.utils.Date date3 = valueObject.getContractStartDate();		
		if ( date3 != null ) 
		{
			value3 = date3.getDate();
		}
		domainObject.setContractStartDate(value3);
		java.util.Date value4 = null;
		ims.framework.utils.Date date4 = valueObject.getContractEndDate();		
		if ( date4 != null ) 
		{
			value4 = date4.getDate();
		}
		domainObject.setContractEndDate(value4);

		return domainObject;
	}

}
