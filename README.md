## Introduction
This repository is my Java implementation of basic data structure and basic sorting algorithm. Data structure include ArrayList, LinkedList, Queue, Stack, Heap, HashTable, Trie, Binary Search Tree. Sorting Algorithm include bubble sort, selection sort, insertion sort, merge sort and quick sort.

## ArrayList
Implemented method:
add(E e)
add(int index)
get()
isEmpty()
remove(int index)
size()
iterator()

## Functions
* 1.User registration and authorization. New users need to confirm their accounts by email.
* 2.Editing user profile. The user can upload new image as profile image and add personal information.
* 3.Users can create a new event by clicking on the map or searching for a certain location.A new event should have begin time, end time, event name, content, category and other optional information including specific location and image.
* 4.Achievement system.The user in HyperMap can gather credits by attending events and creatin event. The different level user will have different level logo shown.
* 5.Users can register for an event and get credits after their attendance being confirmed.
* 6.Event creator can view all the users who have registered the event. And creator can also confirm the attendance of the registered user.


## Deployment
Deployment Environment: Google App Engine + Google Storage
https://hypermap-cmu.appspot.com
(Please contact me at jiawenp1@andrew.cmu.edu if this instance is not running)

## Notes:
I’m sending real Emails for registration and resetting.
Make sure you use a real email address and confirm the link in the email sent to you.
In settings.py, I changed my password in to password for submission.

