def mine(s):
    if len(s) == 1:
        return s[0]
    ct = 0
    ans = 0
    t = 0 
    i = 1
    while s[i] == 0 and i <= len(s):
        i += 1
    while s[t] == 0 and t <= len(s):
        t += 1
    while i < len(s) and i != t:
        if s[t] == s[i]:
            s[i] += 1
            s[t] = 0
            ans = max(ans, mine(s))
            s[t] = s[i] - 1
            s[i] -= 1
            ct += 1
        i += 1
        t += 1
        while i < len(s) and s[i] == 0:
            i += 1
        while t < len(s) and s[t] == 0:
            t += 1
    if ct == 0:
        return max(s)
    return ans


def main():
    with open('248.in', 'r') as f:
        n = int(f.readline())
        s = list(map(lambda x: int(x), f.readlines()))
    with open('248.out', 'w') as f:
        f.write(str(mine(s)))


if __name__ == '__main__':
    main()
