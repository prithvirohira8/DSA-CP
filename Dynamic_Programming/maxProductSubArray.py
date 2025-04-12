# Approach: 

# If we keep multiplying integers (non zero) continuously,
# the product is bound to increase (in terms of magnitude only).
# However the presence of a negative no can affect the value severly and we can obtain
# a product that is the min. Later if we obtain another negative integer the value 
# of the product becomes positive and it can be considered.
# Hence we need to keep track of both the nos, the one that was positive before it became negative,
# the negative no which was highest in magnitude (incase it becomes positve again)
# Based on the behaviour of the examples the final logic can be computed

class Solution(object):
    def maxProduct(self, nums):
        res = max(nums)
        max_product = nums[0]
        min_product = nums[0]
        for i in range(1, len(nums)):
            a = max_product
            b = min_product
            if nums[i] == 0:
                a, b = 1, 1
            max_product = max(a * nums[i], b * nums[i], nums[i])
            min_product = min(a * nums[i], b * nums[i], nums[i])
            res = max(res, max_product)
        return res


# Time Complexity: O(N)