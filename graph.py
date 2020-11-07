import matplotlib.pyplot as plt
import networkx as nx

from matplotlib.backends.backend_agg import FigureCanvasAgg as FigureCanvas
from matplotlib.figure import Figure

def graph():
    n, k, p = 100, 10, 0.1

    G = nx.watts_strogatz_graph(n, k, p)

    nx.draw(G)
    plt.show()

if __name__ == "__main__":
    graph()
