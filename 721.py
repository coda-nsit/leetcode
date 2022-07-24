"""
simple union find
"""

class Solution:
    def getParent(self, email, parents):
        while email != parents[email]:
            email = parents[email]
        return email
        
    def unite(self, email1, email2, parents, sizes):
        parent1 = self.getParent(email1, parents)
        parent2 = self.getParent(email2, parents)
        size1 = sizes[parent1]
        size2 = sizes[parent2]
        if size1 >= size2:
            parents[parent2] = parent1
            sizes[parent1] += size2
        else:
            parents[parent1] = parent2
            sizes[parent2] += size1
            
    def find(self, email1, email2):
        return self.getParent(email1) == self.getParent(email2)
        
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        parents = {}
        
        distinctEmails = set()
        for account in accounts:
            name, emails = account[0], account[1:]
            distinctEmails.update(emails)
        distinctEmails = list(distinctEmails)
        
        parents = {}
        sizes = {}
        for email in distinctEmails:
            parents[email] = email
            sizes[email] = 1
        
        for account in accounts:
            name, emails = account[0], account[1:]
            firstEmail = emails[0]
            for idx in range(1, len(emails)):
                self.unite(firstEmail, emails[idx], parents, sizes)
        
        emailToName = {}
        for account in accounts:
            name, parent = account[0], self.getParent(account[1], parents)
            if parent not in emailToName:
                # print("parent:", parent)
                emailToName[parent] = [name]
            for email in account[1:]:
                emailToName[parent].append(email)
        
        # print(emailToName)
        
        answer = []
        for primaryEmail, values in emailToName.items():
            answer.append(sorted(set([primaryEmail] + values[1:]), reverse=True))
            answer[-1].append(values[0])
            answer[-1] = answer[-1][::-1]
        return answer
            
           
"""
[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

[["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]

[["Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"],["Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"],["Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"],["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]]

[["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]

[["David","David0@m.co","David1@m.co"],["David","David3@m.co","David4@m.co"],["David","David4@m.co","David5@m.co"],["David","David2@m.co","David3@m.co"],["David","David1@m.co","David2@m.co"]]
"""
