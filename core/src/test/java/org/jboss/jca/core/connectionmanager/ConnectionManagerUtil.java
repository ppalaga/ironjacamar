/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.jca.core.connectionmanager;

import java.lang.reflect.Field;

/**
 * Utility class for ConnectionManager
 *
 * @author <a href="jesper.pedersen@jboss.org">Jesper Pedersen</a>
 */
public class ConnectionManagerUtil
{
   /**
    * Constructor
    */
   private ConnectionManagerUtil()
   {
   }

   /**
    * Extract ConnectionManager from the passed object
    * @param obj The object; typically a ConnectionFactory implementation
    * @return The connection manager; <code>null</code> if not found
    */
   public static org.jboss.jca.core.connectionmanager.ConnectionManager extract(Object obj)
   {
      Class<?> clz = obj.getClass();

      while (!Object.class.equals(clz))
      {
         try
         {
            Field[] fields = clz.getDeclaredFields();

            if (fields != null && fields.length > 0)
            {
               for (Field field : fields)
               {
                  Class<?> fieldType = field.getType();
                  if (fieldType.equals(javax.resource.spi.ConnectionManager.class) ||
                      fieldType.equals(org.jboss.jca.core.connectionmanager.ConnectionManager.class))
                  {
                     return (org.jboss.jca.core.connectionmanager.ConnectionManager)field.get(obj);
                  }
               }
            }
         }
         catch (Throwable t)
         {
            // Nothing
         }
         clz = clz.getSuperclass();
      }

      return null;
   }
}