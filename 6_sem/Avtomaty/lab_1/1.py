import random

# 1
print('### 1')
S = 'AA'
n = 0
while 'A' in S:
    print(f"\t{n}. {S}")
    if random.randint(0, 1):
        S = S.replace('A', 'aAb', 1)
    else:
        S = S.replace('A', 'ab', 1)
    n += 1
print(f"Result: \033[3;36mS = {S}\033[0m")

# 2
print('\n-------------------------\n### 2')
S = 'aSb'
n = 0
while 'F' in S or 'S' in S:
    print(f"\t{n}. {S}")
    if random.randint(0, 1):
        S = S.replace('S', 'aSb', 1)
    else:
        S = S.replace('S', 'cFc', 1)
    n += 1
    print(f"\t{n}. {S}")
    if random.randint(0, 1):
        S = S.replace('F', 'cFc', 1)
    else:
        S = S.replace('F', '', 1)
    n += 1
print(f"Result: \033[3;36mS = {S}\033[0m")

# 3
print('\n-------------------------\n### 3')
S = 'aF'
n = 0
while 'F' in S or 'S' in S:
    print(f"\t{n}. {S}")
    if random.randint(0, 1):
        S = S.replace('F', 'Sb', 1)
    elif random.randint(0, 1):
        S = S.replace('F', 'Fb', 1)
    else:
        S = S.replace('F', 'b', 1)
    n += 1
    print(f"\t{n}. {S}")
    S = S.replace('S', 'aF', 1)
    n += 1
print(f"Result: \033[3;36mS = {S}\033[0m")

# 4
print("\n-------------------------\n### 4")


def task_4(s, result):
    if 'A' in s:
        result.union(task_4(s.replace('A', 'aBb', 1), result))
        result.union(task_4(s.replace('A', 'ab', 1), result))
    elif 'B' in s:
        result.union(task_4(s.replace('B', 'aCb', 1), result))
        result.union(task_4(s.replace('B', 'ab', 1), result))
    elif 'C' in s:
        result.union(task_4(s.replace('C', 'aDb', 1), result))
        result.union(task_4(s.replace('C', 'ab', 1), result))
    elif 'D' in s:
        result.union(task_4(s.replace('D', 'ab', 1), result))
    else:
        result.add(s)
    return result


S = 'AA'
result4 = set()
result4 = task_4(S, result4)
print(f"Result for: \033[3;36mS = {S}\033[0m")
n = 1
for i in result4:
    print(f"\t{n}.\t{i}")
    n += 1

# 5
print('\n-------------------------\n### 5')


def task_5(s, result):
    if 'A' in s:
        result.union(task_4(s.replace('A', 'bbB', 1), result))
        result.union(task_4(s.replace('A', 'a', 1), result))
    elif 'B' in s:
        result.union(task_4(s.replace('B', 'ccC', 1), result))
        result.union(task_4(s.replace('B', 'b', 1), result))
    elif 'C' in s:
        result.union(task_4(s.replace('C', 'dD', 1), result))
        result.union(task_4(s.replace('C', 'c', 1), result))
    elif 'D' in s:
        result.union(task_4(s.replace('D', 'd', 1), result))
    else:
        result.add(s)
    return result


S = 'aaA'
result5 = set()
result5 = task_4(S, result5)
print(f"Result for: \033[3;36mS = {S}\033[0m")
n = 1
for i in result5:
    print(f"\t{n}.\t{i}")
    n += 1
