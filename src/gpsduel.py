from heapq import heappush

def hp(pq, item, mindist):
    heappush(pq, (mindist[item], item))


def dijkstra(graph):
    dist_to = [1000000 for i in range(len(graph))]
    prev = [0 for i in range(len(graph))]
    dist_to[len(graph) - 1] = 0
    pq = []
    hp(pq, len(graph) - 1, dist_to)
    while len(pq) != 0:
        curr = pq.pop(0)[1]
        for other in graph[curr]:
            print(curr)
            alt = other[0]
            if dist_to[alt] > dist_to[curr] + other[1]:
                dist_to[alt] = dist_to[curr] + other[1]
                hp(pq, alt, dist_to)
                prev[alt] = curr
    print(prev)
    return dist_to


def main():
    with open('gpsduel.in', 'r') as f:
        N, M = map(int, f.readline().split())
        p = [[] for i in range(N)]
        q = [[] for i in range(N)]
        for i in range(M):
            Ai, Bi, Pi, Qi = map(int, f.readline().split())
            Ai -= 1
            Bi -= 1
            p[Bi].append([Ai, Pi])
            q[Bi].append([Ai, Qi])
    p_dist = dijkstra(p) 
    q_dist = dijkstra(q)


if __name__ == '__main__':
    main()
