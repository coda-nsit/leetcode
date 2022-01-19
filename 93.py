DOTS = 4
class Solution:
    def validateIp(self, ip, s_len):
        for number in ip:
            if not((number[0] == "0" and int(number) == 0 and len(number) == 1) or (number[0] != "0" and 1 <= int(number) <= 255)):
                return False
        return len("".join(ip)) == s_len
    
    def generateIpAddresses(self, s, start_index, end_index, num_dots, curr_ip, answers, s_len):
        if num_dots == 0 and self.validateIp(curr_ip, s_len):
            # print(curr_ip)
            current_string = []
            for num in curr_ip[::-1]:
                current_string.append(num)
            answers.append(".".join(current_string))
            return
        for i in range(start_index, -1, -1):
            curr_ip.append(s[i: end_index + 1])
            self.generateIpAddresses(s, i - 1, i - 1, num_dots - 1, curr_ip, answers, s_len)
            curr_ip.pop()
        return
        
    def restoreIpAddresses(self, s: str) -> List[str]:
        if len(s) < 4 or len(s) > 12 or not all([digit.isnumeric() for digit in s]):
            return []
        answers = []
        self.generateIpAddresses(s, len(s) - 1, len(s) - 1, DOTS, [], answers, len(s))
        return answers
