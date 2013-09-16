package org.ragna.thinkcomp

import org.scalatest.FlatSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GraphFlatSpec extends FlatSpec with GivenWhenThen with ShouldMatchers {

  def fixtureVWY = new {
    val v = new Vertex("v")
    val w = new Vertex("w")
    val y = new Vertex("y")

    val e1 = new Edge(v, w)
    val e2 = new Edge(w, y)

    val g = new Graph(List(v, w, y), List(e1, e2))

    val lg = List((v, w), (w, y))
  }

  "A Graph" should " return an edge when a valid edge is asked " in {
    given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
    val f = fixtureVWY
    when("when get_edge(v, w) is invoked on the graph")
    val result = f.g.get_edge(f.v, f.w)

    then("the requested edge should be returned")
    result should be(f.e1)
  }

  it should "return an empty val if an edge does not exist" in {
    given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
    val f = fixtureVWY

    when("when get_edge(v, y) is invoked on the graph")
    val result = f.g.get_edge(f.v, f.y)

    then("the requested edge should NOT be returned")
    result should be(null)
  }

  it should "remove an edge when it's asked for" in {
    given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
    val f = fixtureVWY

    when("when remove_edge(v, w) is invoked on the graph")
    val result = f.g.remove_edge(f.v, f.w)

    then("a new call get_edge(v, w) shoul be null")
    f.g.get_edge(f.v, f.w) should be(null)
    and("returned edge must refer to requested vertices")
    result.e1 should (equal(f.v) or equal(f.w))
    result.e2 should (equal(f.v) or equal(f.w))

    println(result)
    println(f.g)
  }

  it should "return a set of vertices when asked for" in {
    given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
    val f = fixtureVWY

    when("vertices is invoked ")
    val result = f.g.vertices

    then("a set of vertices should be returned")
    result should be(List(new Vertex("v"), new Vertex("w"), new Vertex("y")))
  }

  it should "return a set of edges when asked for" in {
    given("A Graph with 3 vertices (v,w,y) and 2 edges ((v,w), (w,y))")
    val f = fixtureVWY

    when("edges is invoked ")
    val result = f.g.edges

    then("a set of edges should be returned")
    result should be(List(f.e1, f.e2))
  }

  "A Vertex from a Graph" should " return a list of adjacent vertices when asked for" in {
    given("A Vertex  W")
    val f = fixtureVWY

    when("out_vertices is called")
    val result = f.g.out_vertices(f.w)

    then("a list of adjacent vertices v,y should be returned ")

    result should be(List(f.v, f.y))

  }

  it should "return a list of edges when asked for" in {

    given("A Vertex  W")
    val f = fixtureVWY

    when("out_edges is called")
    val result = f.g.out_vertices(f.w)

    then("a list of adjacent vertices v,y should be returned ")

    println(result)
    result should be(List(f.v, f.y))

  }

}