# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def __init__(self):
        self.forwardPointer = None
        self.flagPalindrome = True
    
    def recursivePalinCheck(self, head):
        if head.next is not None:
            self.recursivePalinCheck(head.next)
        if self.forwardPointer.val != head.val:
            self.flagPalindrome = False
            return
        if self.flagPalindrome is False:
            return False
        self.forwardPointer = self.forwardPointer.next
            
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        self.forwardPointer = head
        self.recursivePalinCheck(head)
        return self.flagPalindrome
