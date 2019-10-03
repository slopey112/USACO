def main():
    with open('crossroad.in', 'r') as f:
        n = int(f.readline())
        cross = list(map(lambda x: x.split(), f.readlines()))
    index = {}
    res = 0
    for c, p in cross:
        if c not in index:
            index[c] = p
        elif index[c] != p:
            res += 1
            index[c] = p
    with open('crossroad.out', 'w') as f:
        f.write(str(res))


if __name__ == '__main__':
    main()
