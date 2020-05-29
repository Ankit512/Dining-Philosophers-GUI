# Dining-Philosophers-With-GUI

<h2>A java based approach to the solution of the famous Dining Philosophers problem which illustrates synchronization issues and techniques for resolving them.<h2>

<h2>Here is the problem description</h2> 

Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.
Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when he has both left and right forks. Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher. After he finishes eating, he needs to put down both forks so they become available to others. A philosopher can take the fork on his right or the one on his left as they become available, but cannot start eating before getting both of them.
Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.
The problem is how to design a discipline of behavior (a concurrent algorithm) such that no philosopher will starve; i.e., each can forever continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.


## Problem
The problem was designed to illustrate the challenges of avoiding deadlock, a system state in which no progress is possible. To see that a proper solution to this problem is not obvious, consider a proposal in which each philosopher is instructed to behave as follows:
think until the left fork is available; when it is, pick it up;
think until the right fork is available; when it is, pick it up;
when both forks are held, eat for a fixed amount of time;
then, put the right fork down;
then, put the left fork down;
repeat from the beginning.

This attempted solution fails because it allows the system to reach a deadlock state, in which no progress is possible. This is a state in which each philosopher has picked up the fork to the left, and is waiting for the fork to the right to become available. With the given instructions, this state can be reached, and when it is reached, the philosophers will eternally wait for each other to release a fork.

Resource starvation might also occur independently of deadlock if a particular philosopher is unable to acquire both forks because of a timing problem. For example there might be a rule that the philosophers put down a fork after waiting ten minutes for the other fork to become available and wait a further ten minutes before making their next attempt. This scheme eliminates the possibility of deadlock (the system can always advance to a different state) but still suffers from the problem of livelock. If all five philosophers appear in the dining room at exactly the same time and each picks up the left fork at the same time the philosophers will wait ten minutes until they all put their forks down and then wait a further ten minutes before they all pick them up again.

Mutual exclusion is the basic idea of the problem; the dining philosophers create a generic and abstract scenario useful for explaining issues of this type. The failures these philosophers may experience are analogous to the difficulties that arise in real computer programming when multiple programs need exclusive access to shared resources. These issues are studied in the branch of concurrent programming. The original problems of Dijkstra were related to external devices like tape drives. However, the difficulties exemplified by the dining philosophers problem arise far more often when multiple processes access sets of data that are being updated. Systems such as operating system kernels use thousands of locks and synchronizations that require strict adherence to methods and protocols if such problems as deadlock, starvation, or data corruption are to be avoided.


## Solution
The solution of the Dining Philosopher Problem is to use a semaphore to represent a fork. A fork can be picked up by executing a wait operation on the semaphore and released by executing a signal semaphore.

The structure of the fork is shown below:

semaphore fork [5];  

Initially the elements of the forks are initialized to 1 as the forks are on the table and not picked up by a philosopher.
The structure of a random philosopher i is given as follows:

```java
do {
wait( fork[i] );
wait( fork[ (i+1) % 5] );
. .
. EATING THE SPAGHETTI
.
signal( fork[i] );
signal( fork[ (i+1) % 5] );
.
. THINKING
.
} while(1);
```

In the above structure, first wait operation is performed on fork[i] and fork[ (i+1) % 5]. This means that the philosopher i has picked up the fork on his sides. Then the eating function is performed.

After that, signal operation is performed on fork[i] and fork[ (i+1) % 5]. This means that the philosopher i has eaten and put down the fork on his sides. Then the philosopher i has eaten and put down the fork on his sides. Then the philosopher goes back to thinking.


## Output
![](nbproject/dining.PNG)

The colured tabs indicate the following:

🟡 - Eating

🔴 - Hungry

🟢 - Thinking


## Conclusion
We have achieved our goal of a simple and efficient solution for the Dining Philosophers.


## Done by
- Ankit Kumar


## References
[Avi Farah](https://www.codeproject.com/Articles/1239410/Dining-Philosophers-Problem)
