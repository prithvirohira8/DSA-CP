# Longest Increasing Subsquence

# Approach (Dynamic Programming):
# If we read the question carefully with the examples we see that all the features
# of the problem cater to making it a DP problem as mentioned in the .txt file in this folder
# If we traverse the array  in the backward direction, we just need the count that is the no's greater
# than the current no in the forward direction. 
# If we store that count in an array, then we can utilize that no while traversing the array,
# in the backward direction and also check if the no in the nums array at the index of the count is 
# greater than the no at the index where the loop is iterating in the backward direction. 
# If yes the no can be updated using max considering other possible cases where the count can be greater
# at other indices ahead and the if loop satisfies the condition too

class Solution(object):
    def lengthOfLIS(self, nums):
        LIS = [0] * len(nums)

        for i in range(len(nums) - 1, -1, -1):
            for j in range(i + 1, len(nums)):
                if(nums[i] < nums[j]):
                    LIS[i] = max(LIS[i], LIS[j] + 1)
        return (max(LIS) + 1)
        
# Time Complexity: O(N^2)