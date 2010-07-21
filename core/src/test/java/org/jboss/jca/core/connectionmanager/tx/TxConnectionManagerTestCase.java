/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008-2009, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.jca.core.connectionmanager.tx;

import org.jboss.jca.core.api.ConnectionManager;
import org.jboss.jca.core.connectionmanager.ConnectionManagerFactory;
import org.jboss.jca.core.connectionmanager.common.MockConnectionRequestInfo;
import org.jboss.jca.core.connectionmanager.common.MockHandle;
import org.jboss.jca.core.connectionmanager.common.MockManagedConnectionFactory;
import org.jboss.jca.core.connectionmanager.pool.api.Pool;
import org.jboss.jca.core.connectionmanager.pool.api.PoolConfiguration;
import org.jboss.jca.core.connectionmanager.pool.api.PoolFactory;
import org.jboss.jca.core.connectionmanager.pool.api.PoolStrategy;
import org.jboss.jca.core.connectionmanager.transaction.TransactionSynchronizer;
import org.jboss.jca.core.connectionmanager.tx.TxConnectionManager;
import org.jboss.jca.core.workmanager.unit.WorkManagerTestCase;
import org.jboss.jca.embedded.EmbeddedJCA;

import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.TransactionSupport.TransactionSupportLevel;
import javax.transaction.TransactionManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * TxConnectionManagerTestCase.
 * @author <a href="mailto:gurkanerdogdu@yahoo.com">Gurkan Erdogdu</a> 
 * @author <a href="mailto:jesper.pedersen@jboss.org">Jesper Pedersen</a> 
 */
public class TxConnectionManagerTestCase
{
   /**Embedded JCA*/
   private static EmbeddedJCA embedded = null;
   
   /**
    * testTxAllocateConnection.
    * @throws Throwable for exception
    */
   @Test
   public void testAllocateConnection() throws Throwable
   {
      TransactionManager tm = embedded.lookup("RealTransactionManager", TransactionManager.class);
      assertNotNull(tm);
      
      ManagedConnectionFactory mcf = new MockManagedConnectionFactory();
      PoolConfiguration pc = new PoolConfiguration();      
      PoolFactory pf = new PoolFactory();      
      
      Pool pool = pf.create(PoolStrategy.ONE_POOL, mcf, pc, true);
      
      ConnectionManagerFactory cmf = new ConnectionManagerFactory();
      ConnectionManager connectionManager = cmf.create(TransactionSupportLevel.LocalTransaction, pool, tm);
      assertNotNull(connectionManager);
      
      assertTrue(connectionManager instanceof TxConnectionManager);
      
      TxConnectionManager txConnectionManager = (TxConnectionManager)connectionManager;
      
      assertNotNull(txConnectionManager.getCachedConnectionManager());
      
      TransactionManager transactionManager = txConnectionManager.getTransactionManager();
      TransactionSynchronizer.setTransactionManager(transactionManager);
      
      assertNotNull(transactionManager);
      
      transactionManager.begin();
      
      Object handle = connectionManager.allocateConnection(mcf, new MockConnectionRequestInfo());
      assertNotNull(handle);
      
      assertTrue(handle instanceof MockHandle);
      
      transactionManager.commit();
   }
   
   /**
    * testConnectionEventListenerConnectionClosed.
    * @throws Exception for exception
    */
   @Test
   public void testConnectionEventListenerConnectionClosed() throws Exception
   {
      
   }
   
   /**
    * testSynchronizationAfterCompletion.
    * @throws Exception for exception
    */
   @Test
   public void testSynchronizationAfterCompletion() throws Exception
   {
      
   }
   
   /**
    * testSynchronizationAfterCompletionTxTimeout.
    * @throws Exception for exception
    */
   @Test
   public void testSynchronizationAfterCompletionTxTimeout() throws Exception
   {
      
   }
   
   /**
    * testGetManagedConnection.
    * @throws Exception for exception
    */
   @Test
   public void testGetManagedConnection() throws Exception
   {
      
   }
   
   /**
    * testGetManagedConnectionTimeout.
    * @throws Exception for exception
    */
   @Test
   public void testGetManagedConnectionTimeout() throws Exception
   {
      
   }
   
   /**
    * testGetManagedConnectionTrackByTx.
    * @throws Exception for exception
    */
   @Test
   public void testGetManagedConnectionTrackByTx() throws Exception
   {
      
   }
   
   /**
    * testGetManagedConnectionTimeoutTrackByTx.
    * @throws Exception for exception
    */
   @Test
   public void testGetManagedConnectionTimeoutTrackByTx() throws Exception
   {
      
   }
   
   /**
    * testConnectionError.
    * @throws Exception for exception.
    */
   @Test
   public void testConnectionError() throws Exception
   {
      
   }
   
   /**
    * testConnectionErrorTrackByTx.
    * @throws Exception for exception
    */
   @Test
   public void testConnectionErrorTrackByTx() throws Exception
   {
      
   }
   
   /**
    * testSimulateConnectionError.
    * @throws Exception for exception.
    */
   @Test
   public void testSimulateConnectionError() throws Exception
   {
      
   }
   
   /**
    * testSimulateConnectionErrorTrackByTx.
    * @throws Exception for exception
    */
   @Test
   public void testSimulateConnectionErrorTrackByTx() throws Exception
   {
      
   }
   
   /**
    * Lifecycle start, before the suite is executed
    * @throws Throwable throwable exception 
    */
   @BeforeClass
   public static void beforeClass() throws Throwable
   {
      // Create and set an embedded JCA instance
      embedded = new EmbeddedJCA(false);

      // Startup
      embedded.startup();

      // Deploy Naming and Transaction
      embedded.deploy(WorkManagerTestCase.class.getClassLoader(), "naming-jboss-beans.xml");
      embedded.deploy(WorkManagerTestCase.class.getClassLoader(), "transaction-jboss-beans.xml");
   }
   
   /**
    * Lifecycle stop, after the suite is executed
    * @throws Throwable throwable exception 
    */
   @AfterClass
   public static void afterClass() throws Throwable
   {
      // Undeploy Transaction and Naming
      embedded.undeploy(WorkManagerTestCase.class.getClassLoader(), "transaction-jboss-beans.xml");
      embedded.undeploy(WorkManagerTestCase.class.getClassLoader(), "naming-jboss-beans.xml");

      // Shutdown embedded
      embedded.shutdown();

      // Set embedded to null
      embedded = null;
   }   
   
}