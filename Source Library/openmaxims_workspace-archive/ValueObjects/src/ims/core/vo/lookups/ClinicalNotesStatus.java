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

package ims.core.vo.lookups;

import ims.framework.cn.data.TreeNode;
import java.util.ArrayList;
import ims.framework.utils.Image;
import ims.framework.utils.Color;

public class ClinicalNotesStatus extends ims.vo.LookupInstVo implements TreeNode
{
	private static final long serialVersionUID = 1L;

	public ClinicalNotesStatus()
	{
		super();
	}
	public ClinicalNotesStatus(int id)
	{
		super(id, "", true);
	}
	public ClinicalNotesStatus(int id, String text, boolean active)
	{
		super(id, text, active, null, null, null);
	}
	public ClinicalNotesStatus(int id, String text, boolean active, ClinicalNotesStatus parent, Image image)
	{
		super(id, text, active, parent, image);
	}
	public ClinicalNotesStatus(int id, String text, boolean active, ClinicalNotesStatus parent, Image image, Color color)
	{
		super(id, text, active, parent, image, color);
	}
	public ClinicalNotesStatus(int id, String text, boolean active, ClinicalNotesStatus parent, Image image, Color color, int order)
	{
		super(id, text, active, parent, image, color, order);
	}
	public static ClinicalNotesStatus buildLookup(ims.vo.LookupInstanceBean bean)
	{
		return new ClinicalNotesStatus(bean.getId(), bean.getText(), bean.isActive());
	}
	public String toString()
	{
		if(getText() != null)
			return getText();
		return "";
	}
	public TreeNode getParentNode()
	{
		return (ClinicalNotesStatus)super.getParentInstance();
	}
	public ClinicalNotesStatus getParent()
	{
		return (ClinicalNotesStatus)super.getParentInstance();
	}
	public void setParent(ClinicalNotesStatus parent)
	{
		super.setParentInstance(parent);
	}
	public TreeNode[] getChildren()
	{
		ArrayList children = super.getChildInstances();
		ClinicalNotesStatus[] typedChildren = new ClinicalNotesStatus[children.size()];
		for (int i = 0; i < children.size(); i++)
		{
			typedChildren[i] = (ClinicalNotesStatus)children.get(i);
		}
		return typedChildren;
	}
	public int addChild(TreeNode child)
	{
		if (child instanceof ClinicalNotesStatus)
		{
			super.addChild((ClinicalNotesStatus)child);
		}
		return super.getChildInstances().size();
	}
	public int removeChild(TreeNode child)
	{
		if (child instanceof ClinicalNotesStatus)
		{
			super.removeChild((ClinicalNotesStatus)child);
		}
		return super.getChildInstances().size();
	}
	public Image getExpandedImage()
	{
		return super.getImage();
	}
	public Image getCollapsedImage()
	{
		return super.getImage();
	}
	public static ims.framework.IItemCollection getNegativeInstancesAsIItemCollection()
	{
		ClinicalNotesStatusCollection result = new ClinicalNotesStatusCollection();
		result.add(DRAFT);
		result.add(PREVALIDATION);
		result.add(CORRECTED);
		result.add(ACTIVE);
		result.add(CONFIRMED);
		return result;
	}
	public static ClinicalNotesStatus[] getNegativeInstances()
	{
		ClinicalNotesStatus[] instances = new ClinicalNotesStatus[5];
		instances[0] = DRAFT;
		instances[1] = PREVALIDATION;
		instances[2] = CORRECTED;
		instances[3] = ACTIVE;
		instances[4] = CONFIRMED;
		return instances;
	}
	public static String[] getNegativeInstanceNames()
	{
		String[] negativeInstances = new String[5];
		negativeInstances[0] = "DRAFT";
		negativeInstances[1] = "PREVALIDATION";
		negativeInstances[2] = "CORRECTED";
		negativeInstances[3] = "ACTIVE";
		negativeInstances[4] = "CONFIRMED";
		return negativeInstances;
	}
	public static ClinicalNotesStatus getNegativeInstance(String name)
	{
		if(name == null)
			return null;
		String[] negativeInstances = getNegativeInstanceNames();
		for (int i = 0; i < negativeInstances.length; i++)
		{
			if(negativeInstances[i].equals(name))
				return getNegativeInstances()[i];
		}
		return null;
	}
	public static ClinicalNotesStatus getNegativeInstance(Integer id)
	{
		if(id == null)
			return null;
		ClinicalNotesStatus[] negativeInstances = getNegativeInstances();
		for (int i = 0; i < negativeInstances.length; i++)
		{
			if(negativeInstances[i].getID() == id)
				return negativeInstances[i];
		}
		return null;
	}
	public int getTypeId()
	{
		return TYPE_ID;
	}
	public static final int TYPE_ID = 1021143;
	public static final ClinicalNotesStatus DRAFT = new ClinicalNotesStatus(-691, "Draft", true, null, null, Color.Default);
	public static final ClinicalNotesStatus PREVALIDATION = new ClinicalNotesStatus(-692, "Requires Validation", true, null, null, Color.Default);
	public static final ClinicalNotesStatus CORRECTED = new ClinicalNotesStatus(-694, "Corrected", true, null, null, Color.Default);
	public static final ClinicalNotesStatus ACTIVE = new ClinicalNotesStatus(-1158, "Active", true, null, null, Color.Default);
	public static final ClinicalNotesStatus CONFIRMED = new ClinicalNotesStatus(-2162, "Confirmed", true, null, null, Color.Default);
}
