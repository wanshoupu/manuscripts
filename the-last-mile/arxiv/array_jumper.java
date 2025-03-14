/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false
 */
public class JumpArray {
    public boolean canJump(int[] nums) {
        for (int i = 0, frontline = 0; i < nums.length; ++i) {
            if (frontline < i)
                return false;
            else
                frontline = Math.max(frontline, nums[i] + i);
        }
        return true;
    }
}
