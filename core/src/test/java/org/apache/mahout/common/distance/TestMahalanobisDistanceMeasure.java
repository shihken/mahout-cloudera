/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.common.distance;

import org.apache.mahout.common.MahoutTestCase;
import org.apache.mahout.math.Matrix;
import org.apache.mahout.math.DenseMatrix;
import org.apache.mahout.math.DenseVector;


//To launch this test only : mvn test -Dtest=org.apache.mahout.common.distance.TestMahalanobisDistanceMeasure
public class TestMahalanobisDistanceMeasure extends MahoutTestCase {
  
  public void testMeasure() {    
    double tolerance=10E-5;
    double[][] invCovValues = { { 2.2, 0.4 }, { 0.4, 2.8 } };
    double[] meanValues = { -2.3, -0.9 };
    DenseMatrix invCov = new DenseMatrix(invCovValues);
    DenseVector meanVector = new DenseVector(meanValues);
    MahalanobisDistanceMeasure distanceMeasure = new MahalanobisDistanceMeasure();
    distanceMeasure.setInverseCovarianceMatrix(invCov);
    distanceMeasure.setMeanVector(meanVector);
    double[] v1 = { -1.9, -2.3 };
    double[] v2 = { -2.9, -1.3 };
    double dist=distanceMeasure.distance(new DenseVector(v1),new DenseVector(v2));
    assertEquals(2.0493901531919194,dist,tolerance);
    //now set the covariance Matrix
    distanceMeasure.setCovarianceMatrix(invCov);
    //check the inverse covariance times covariance equals identity 
    Matrix identity=distanceMeasure.getInverseCovarianceMatrix().times(invCov);
    assertEquals(1,identity.get(0,0),tolerance);
    assertEquals(1,identity.get(1,1),tolerance);
    assertEquals(0,identity.get(1,0),tolerance);
    assertEquals(0,identity.get(0,1),tolerance);
  }
  
}
