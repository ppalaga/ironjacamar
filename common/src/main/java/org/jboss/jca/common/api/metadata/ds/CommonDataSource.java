/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.jca.common.api.metadata.ds;

import org.jboss.jca.common.api.metadata.JCAMetadata;

/**
 *
 * A CommonDataSource.
 *
 * @author <a href="stefano.maestri@jboss.com">Stefano Maestri</a>
 *
 */
public interface CommonDataSource extends JCAMetadata
{

   /**
    * Get the minPoolSize.
    *
    * @return the minPoolSize.
    */

   public Integer getMinPoolSize();

   /**
    * Get the maxPoolSize.
    *
    * @return the maxPoolSize.
    */

   public Integer getMaxPoolSize();

   /**
    * Get the prefill.
    *
    * @return the prefill.
    */

   public boolean isPrefill();

   /**
    * Get the transactionIsolation.
    *
    * @return the transactionIsolation.
    */

   public TransactionIsolation getTransactionIsolation();

   /**
    * Get the timeOut
    *
    * @return the timeOut.
    */

   public TimeOut getTimeOut();

   /**
    * Get the security.
    *
    * @return the security.
    */

   public Security getSecurity();

   /**
    * Get the validation.
    *
    * @return the validation.
    */

   public Validation getValidation();

   /**
    * Get the useJavaContext.
    *
    * @return the useJavaContext.
    */

   public boolean isUseJavaContext();

   /**
    * Get the poolName.
    *
    * @return the poolName.
    */

   public String getPoolName();

   /**
    * Get the enabled.
    *
    * @return the enabled.
    */

   public boolean isEnabled();

   /**
    * Get the jndiName.
    *
    * @return the jndiName.
    */

   public String getJndiName();

}