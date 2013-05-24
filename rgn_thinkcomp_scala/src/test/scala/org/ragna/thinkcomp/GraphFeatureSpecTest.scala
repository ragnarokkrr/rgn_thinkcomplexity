package org.ragna.thinkcomp

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.junit.JUnitRunner
import org.junit.runner._
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class GraphFeatureSpecTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("The user can define Vertices and Edges in Graph") {
    info("As a programmer ")
    info("I want to be able to define Vertices and Edges in Graph")
    info("So that I can get the edges by label")

    scenario("get_edge is invoked in a non-empty Graph") {
      given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
      val v = new Vertex("v")
      val w = new Vertex("w")
      val y = new Vertex("y")

      val e1 = new Edge(v, w)
      val e2 = new Edge(w, y)

      val g = new Graph(List(v, w, y), List(e1, e2))

      when("when get_edge(v, w) is invoked on the graph")
      val result = g.get_edge(v, w)

      then("the requested edge should be returned")
      result should be(e1)
    }

    scenario("get_edge is invoked in a non-empty Graph for an unknown edge") {

      given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
      val v = new Vertex("v")
      val w = new Vertex("w")
      val y = new Vertex("y")

      val e1 = new Edge(v, w)
      val e2 = new Edge(w, y)

      val g = new Graph(List(v, w, y), List(e1, e2))

      when("when get_edge(v, y) is invoked on the graph")
      val result = g.get_edge(v, y)
      
      //must do then

    }
  }

}