package org.ragna.thinkcomp

import collection.mutable.{ Map => MutableMap }

/**
 * @author ragnarokkrr
 */
class Graph {
  var v: Map[Vertex, MutableMap[Vertex, Edge]] = Map[Vertex, MutableMap[Vertex, Edge]]()

  /**
   * Create a new graph. (vs) is a list of vertices;
   * (es) a list of edges
   * @param vs
   * @param es
   */
  def this(vs: List[Vertex], es: List[Edge]) {
    this()
    for (v <- vs) {
      add_vertex(v)
    }
    for (e <- es) {
      add_edge(e)
    }
  }

  /**
   * add (v) to the graph
   * @param v
   */
  def add_vertex(v: Vertex) = {
    this.v += (v -> MutableMap[Vertex, Edge]())
  }

  /**
   * add (e) to the graph by adding an entry in both directions.
   * if there is already an edge connecting the Vertices,
   * the new edge replaces it
   * @param e
   * @return
   */
  def add_edge(e: Edge) = {
    val v = e.e1
    val w = e.e2

    (this.v(v)) += (w -> e)
    (this.v(w)) += (v -> e)
  }

  def add_all_edges(v_pairs: List[(Vertex, Vertex)]) {

    if (this.v.size > 0) throw new GraphException
    var vertices = Set[Vertex]()
    v_pairs.foreach { vp =>
      vertices += vp._1
      vertices += vp._2
    }
    vertices.foreach { vx => add_vertex(vx) }

    v_pairs.foreach { vp =>
      add_edge(new Edge(vp._1, vp._2))
    }
  }

  /**
   * Get edge object by both vertex searching in vertices map v
   * @param vx1
   * @param vx2
   * @return Edge object
   */
  def get_edge(vx1: Vertex, vx2: Vertex): Edge = {
    this.v.foreach {
      case (k, v) =>
        v.foreach {
          case (k2, v2) =>
            if (k == vx1 && k2 == vx2 || k == vx2 && k2 == vx1)
              return v2
        }
    }
    null
  }

  def remove_edge(vx1: Vertex, vx2: Vertex): Edge = {
    val e = get_edge(vx1, vx2)
    this.v.foreach {
      case (k, v) =>
        v.foreach {
          case (k2, v2) =>
            if (k == vx1 && k2 == vx2) {
              v -= vx2
            }
            if (k == vx2 && k2 == vx1) {
              v -= vx1
            }
        }
    }
    if (this.v(vx1).size == 0) {
      this.v -= vx1
    }
    if (this.v(vx2).size == 0) {
      this.v -= vx2
    }
    e
  }

  def vertices(): List[Vertex] = {
    var ret = List[Vertex]()
    ret ++= this.v.keySet
    ret
  }

  def edges(): List[Edge] = {
    var eSet = Set[Edge]()
    this.v.values.foreach {
      case innerVals =>
        eSet ++= innerVals.values
    }

    eSet.toList
  }

  def out_vertices(vx: Vertex): List[Vertex] = {
    val v = (this.v(vx))
    v.keySet.toList
  }

  def out_edges(vx: Vertex): List[Edge] = {
    val v = (this.v(vx))
    v.values.toSet.toList
  }

  override def toString(): String = {
    String.format("Graph(%s)", v)
  }
}

/**
 * @author Nilseu Padilha	ragnarokkrr.blog@gmail.com
 *
 */
class GraphException extends Exception

/**
 * Vertex for a graph modeled with a simple label
 * @author ragnarokkrr
 *
 */
class Vertex(var label: String = "") extends Equals {
  override def toString(): String = {
    String.format("Vertex('%s')", label)
  }

  def canEqual(other: Any) = {
    other.isInstanceOf[Vertex]
  }

  override def equals(other: Any) = {
    other match {
      case that: Vertex => that.canEqual(Vertex.this) && label == that.label
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime + label.hashCode
  }
}


class Edge(val e1: Vertex, val e2: Vertex) extends Equals {
  override def toString(): String = {
    String.format("Edge(%s,%s)", e1, e2)
  }

  def canEqual(other: Any) = {
    other.isInstanceOf[org.ragna.thinkcomp.Edge]
  }

  override def equals(other: Any) = {
    other match {
      case that: org.ragna.thinkcomp.Edge => that.canEqual(Edge.this) && e1 == that.e1 && e2 == that.e2
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + e1.hashCode) + e2.hashCode
  }

}

/**
 * Companion object
 * @author ragnarokkrr
 *
 */
object Graph {

  def main(args: Array[String]) {
    println("AAAAAA")

    val v = new Vertex("v")
    val w = new Vertex("w")
    val y = new Vertex("y")

    val e1 = new Edge(v, w)
    val e2 = new Edge(w, y)

    println(e1)

    val g = new Graph(List(v, w, y), List(e1, e2))

    println(g)

    println("edge: " + g.get_edge(v, w))
  }

}
