'''
Created on 15/05/2013

@author: ragnarokkrr
'''
class Graph(dict):
    def __init__(self, vs=[], es=[]):
        """create a new graph . (vs) is a list of vertices;
        (es) a list of edges"""
        for v in vs:
            self.add_vertex(v)
        
        for e in es:
            self.add_edge(e)
            
    def add_vertex(self, v):
        """add (v) to the graph"""
        self[v] = {}
    
    def add_edge(self, e):
        """ add (e) to the graph by adding an entry in both directions.
        if there is already an edge connecting the Vertices, the new 
        edge replaces it
        """
        v, w = e 
        self[v][w] = e
        self[w][v] = e
        
    def get_edge(self, v1, v2):
        for k,v in self.iteritems():
            for k2,v2 in v.iteritems():
                ok = k == v2[0] and k2 == v2[1] or k == v2[1] and k2 == v2[0]
                if(ok):
                    return v2
        return None
    
class Vertex(object):
    def __init__(self, label=''):
        self.label = label
        
    def __repr__(self):
        return 'Vertex(%s)' % repr(self.label)
    
    __str__ = __repr__
    
class Edge(tuple):
    def __new__(cls, e1, e2):
        return tuple.__new__(cls, (e1, e2))
    
    def __repr__(self):
        return 'Edge(%s,%s)' % (repr(self[0]), repr(self[1]))
    
    __str__ = __repr__

if __name__ == '__main__':
    print "AAAAAA"
    v = Vertex('v')
    w = Vertex('w')
    y = Vertex('t')

    e = Edge(v, w)
    e2 = Edge(w, y)

    print e

    g = Graph([v, w, y], [e, e2])
    print g
    print "Edge: ",  g.get_edge(v, w)
    
    
