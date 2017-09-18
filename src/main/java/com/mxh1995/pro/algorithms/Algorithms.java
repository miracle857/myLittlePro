package com.mxh1995.pro.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import org.junit.Test;

public class Algorithms {
	static {
		i = 0;
	}
	static int i = 1;

	/*----------简单二叉树-----------*/
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public class ListNode {
		int val;
		ListNode next;
		
		ListNode(){
			this.next = null;
			this.val = 0;
		}
		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	ListNode removeNthFromEnd(ListNode head, int n) {
		int result = 0;
		ListNode h = head;
		while (h != null) {
			result++;
			h = h.next;
		}
		h = head;
		ListNode pre = null;
		while (h != null) {
			if (pre == null && result == n) {
				head = head.next;
				return head;
			}
			if (result == n) {
				pre.next = h.next;
				break;
			}
			result--;
			pre = h;
			h = h.next;
		}
		return head;
	}

	public int lengthOfLastWord(String s) {
		int result = 0;
		int temp = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				temp++;
			} else if (s.charAt(i) == ' ' && i == s.length() - 1) {

			} else {
				temp = 0;
			}
			result = temp;
		}
		return result;
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < nums.length - 2; i++) {
			int low = i + 1;
			int hi = nums.length - 1;
			while (low < hi) {
				int sum = nums[i] + nums[hi] + nums[low];
				if (sum == target) {
					return target;
				} else if (sum < target) {
					low++;
				} else {
					hi--;
				}

				if (Math.abs(sum - target) <= Math.abs(result - target)) {
					result = sum;
				}
			}
		}
		return result;
	}

	/**
	 * For example, given array S = [-1, 0, 1, 2, -1, -4],
	 * 
	 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
	 * 
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			int low = i + 1;
			int high = nums.length - 1;
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				while (low < high) {
					if (nums[low] + nums[high] + nums[i] == 0) {
						list.add(Arrays.asList(nums[high], nums[low], nums[i]));
						while (low < high && nums[low] == nums[low + 1])
							low++;
						while (low < high && nums[high] == nums[high - 1])
							high--;
						low++;
						high--;
					} else if (nums[low] + nums[high] + nums[i] < 0) {
						low++;
					} else {
						high--;
					}
				}
			}
		}
		return list;
	}

	public int[] searchRange(int[] nums, int target) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				list.add(i);
			}
		}
		int[] result = new int[] { list.get(0), list.get(list.size() - 1) };
		return result;
	}

	/**
	 * Given a string, find the length of the longest substring without
	 * repeating characters.
	 *
	 * Examples:
	 * 
	 * Given "abcabcbb", the answer is "abc", which the length is 3.
	 * 
	 * Given "bbbbb", the answer is "b", with the length of 1.
	 * 
	 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
	 * answer must be a substring, "pwke" is a subsequence and not a substring.
	 * 
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); ++i) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	/**
	 * Reverse digits of an integer.
	 * 
	 * Example1: x = 123, return 321.
	 * 
	 * Example2: x = -123, return -321.
	 * 
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		int result = 0;
		while (x != 0) {
			result *= 10;
			result += x % 10;
			x = x / 10;
		}
		return result;
	}

	public int reverse1(int x) {
		int result = 0;
		while (x != 0) {
			int tail = x % 10;
			int newResult = result * 10 + tail;
			if ((newResult - tail) / 10 != result) {
				return 0;
			}
			result = newResult;
			x = x / 10;
		}
		return result;
	}

	/**
	 * 给定n个非负整数a1，a2，...，an，其中每个表示坐标（i，ai）处的点。 绘制n条垂直线， 使得线i的两个端点处于（i，ai）和（i，0）。
	 * 找到两条线，它们与x轴一起形成容器，使得容器含有最多的水。
	 */
	public int maxArea(int[] height) {
		int max = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				if (i != j) {
					max = Math.max(max, (j - i) * (Math.min(height[i], height[j])));
				}
			}
		}
		return max;
	}

	public boolean isPalindrome(int x) {
		String s = String.valueOf(x);
		int begin = 0, end = s.length() - 1;
		while (begin < end) {
			if (s.charAt(begin) != s.charAt(end)) {
				return false;
			}
			begin++;
			end--;
		}
		return true;
	}

	/**
	 * Find the contiguous subarray within an array (containing at least one
	 * number) which has the largest sum.
	 * 
	 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous
	 * subarray [4,-1,2,1] has the largest sum = 6.
	 * 
	 */
	public int maxSubArray(int[] nums) {
		int begin = 0;
		int end = nums.length - 1;
		int max = -Integer.MAX_VALUE;
		if (nums.length == 1) {
			return nums[0];
		}
		while (begin <= end) {
			int sum = 0;
			for (int i = begin; i <= end; i++) {
				sum += nums[i];
			}
			max = Math.max(sum, max);

			begin++;
		}

		return max;
	}

	public int maxSubArray1(int[] A) {
		int maxSoFar = A[0], maxEndingHere = A[0];
		for (int i = 1; i < A.length; ++i) {
			maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

	// TODO
	public String longestCommonPrefix(String[] strs) {
		StringBuilder stringBuider = new StringBuilder();
		for (int k = 0; k < strs[0].length(); k++) {
			for (int i = 0; i < strs.length; i++) {
				stringBuider.append(strs[i].charAt(k));

			}
		}
		return null;
	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		stack.push(s.charAt(0));
		int i = 1;
		while (i < s.length()) {
			if (stack.isEmpty()) {
				i++;
				stack.push(s.charAt(i));
				continue;
			}
			Character peek = stack.peek();
			if ((peek == '{' && s.charAt(i) == '}') || (peek == '(' && s.charAt(i) == ')')
					|| (peek == '[' && s.charAt(i) == ']')) {
				stack.pop();
			} else {
				stack.push(s.charAt(i));
			}
			i++;
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 计算二进制数中1的个数
	 */
	public int calCountNum1(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) == 1) {
				count++;
			}
			n = n >>> 1;
		}
		return count;
	}

	/**
	 * Given an array of integers that is already sorted in ascending order,
	 * find two numbers such that they add up to a specific target number.
	 * 
	 * The function twoSum should return indices of the two numbers such that
	 * they add up to the target, where index1 must be less than index2. Please
	 * note that your returned answers (both index1 and index2) are not
	 * zero-based.
	 * 
	 * You may assume that each input would have exactly one solution and you
	 * may not use the same element twice.
	 * 
	 * Input: numbers={2, 7, 11, 15}, target=9 .
	 * 
	 * Output: index1=1, index2=2
	 */
	public int[] twoSum(int[] numbers, int target) {
		int begin = 0;
		int end = numbers.length - 1;
		while (begin < end) {

			if ((numbers[end] + numbers[begin]) == target) {
				return new int[] { begin + 1, end + 1 };
			}
			if ((Math.abs(numbers[end - 1] + numbers[begin] - target)
					- Math.abs(numbers[end] + numbers[begin + 1] - target)) > 0) {
				begin++;
			} else {
				end--;
			}
		}
		return null;
	}

	// TODO
	public int reverseBits(int n) {
		int result = 0;
		int k = 0;
		while (k++ <= 31) {
			result = result << 1 + n & 1;
			n = n << 1;
		}
		return result;
	}

	public int parseInt(String s, int radix) throws Exception {
		/*
		 * WARNING: This method may be invoked early during VM initialization
		 * before IntegerCache is initialized. Care must be taken to not use the
		 * valueOf method.
		 */

		if (s == null) {
			throw new NumberFormatException("null");
		}

		if (radix < Character.MIN_RADIX) {
			throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
		}

		if (radix > Character.MAX_RADIX) {
			throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
		}

		int result = 0;
		boolean negative = false;
		int i = 0, len = s.length();
		int limit = -Integer.MAX_VALUE;
		int multmin;
		int digit;

		if (len > 0) {
			char firstChar = s.charAt(0);
			if (firstChar < '0') { // Possible leading "+" or "-"
				if (firstChar == '-') {
					negative = true;
					limit = Integer.MIN_VALUE;
				} else if (firstChar != '+')
					throw new Exception(s);

				if (len == 1) // Cannot have lone "+" or "-"
					throw new Exception(s);
				i++;
			}
			multmin = limit / radix;
			while (i < len) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = Character.digit(s.charAt(i++), radix);
				if (digit < 0) {
					throw new Exception(s);
				}
				if (result < multmin) {
					throw new Exception(s);
				}
				result *= radix;
				if (result < limit + digit) {
					throw new Exception(s);
				}
				result -= digit;
			}
		} else {
			throw new Exception(s);
		}
		return negative ? result : -result;
	}

	public void rotate(int[] nums, int k) {
		if (k == 0)
			return;
		if (k > nums.length)
			k = k % nums.length;
		int[] result = new int[nums.length];
		int p = nums.length - k;
		for (int i = 0; i < nums.length; i++) {
			if (p < nums.length)
				result[i] = nums[p++];
			else
				result[i] = nums[p++ - nums.length];
		}
	}

	public char findTheDifference(String s, String t) {
		char c = 0;
		for (int i = 0; i < s.length(); ++i) {
			c ^= s.charAt(i);
		}
		for (int i = 0; i < t.length(); ++i) {
			c ^= t.charAt(i);
		}
		return c;
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public void testForeach() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		// for (Integer integer : list) {
		// if(integer.equals(1)){
		// list.remove(1);
		// }
		// } //error
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next == 1) {
				iterator.remove();
			}
		}

	}

	public int findComplement(int num) {
		StringBuilder sb = new StringBuilder();
		while (num != 0) {
			if ((num & 1) == 1) {
				sb.append(0);
			} else {
				sb.append(1);
			}
			num >>>= 1;
		}
		sb.reverse();
		return Integer.parseInt(sb.toString(), 2);
	}

	public String reverseString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	// public String reverseString2(String s, int k) {
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < s.length(); i++) {
	// if( i < k){
	// sb.append(s.charAt(k-1-i));
	// }
	// if(s.length() - i <= 2){
	// sb.append(s.charAt(2*s.length()-1-k));
	// }
	// sb.append(s.charAt(i));
	// }
	// return sb.toString();
	// }

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> list = new ArrayList<>();
		list.add(root.val / 1.0);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (queue.isEmpty()) {
			for (int i = 0; i < queue.size(); i++) {
				TreeNode poll = queue.poll();
				queue.add(poll.left);
				queue.add(poll.right);
			}
		}
		return list;
	}

	public int[][] reconstructQueue(int[][] people) {
		// pick up the tallest guy first
		// when insert the next tall guy, just need to insert him into kth
		// position
		// repeat until all people are inserted into list
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? -o1[0] + o2[0] : o1[1] - o2[1];
			}
		});
		List<int[]> res = new LinkedList<>();
		for (int[] cur : people) {
			res.add(cur[1], cur);
		}
		return res.toArray(new int[people.length][]);
	}

	/**
	 * 快速排序
	 * 
	 * @param A
	 * @param start
	 * @param end
	 */
	public void quickSort(int[] A, int start, int end) {
		if (start >= end)
			return;
		int mid = A[start];
		int s = start;
		int e = end;
		while (s != e) {
			while (s < e && A[e] >= mid)
				e--;
			while (s < e && A[s] <= mid)
				s++;
			if (s < e) {
				int temp = A[s];
				A[s] = A[e];
				A[e] = temp;
			}
		}
		A[start] = A[s];
		A[s] = mid;

		quickSort(A, start, e - 1);
		quickSort(A, e + 1, end);
	}

	/**
	 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some
	 * elements appear twice and others appear once.
	 * 
	 * Find all the elements that appear twice in this array.
	 * 
	 * Could you do it without extra space and in O(n) runtime?
	 * 
	 * Example:
	 * 
	 * Input: [4,3,2,7,8,2,3,1]
	 * 
	 * Output: [2,3]
	 * 
	 */
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] < 0)
				res.add(Math.abs(index + 1));
			nums[index] = -nums[index];
		}
		return res;
	}

	/**
	 * 计算单词合法性。 XXXX,Xxxx,xxx 都合法，其他不合法 比如 xxXXx XxxX
	 * 
	 * @param word
	 * @return
	 */
	public boolean detectCapitalUse(String word) {
		char i = word.charAt(0);
		int up = 0;
		int low = 0;

		for (int k = 0; k < word.length(); k++) {
			if ('Z' - word.charAt(k) >= 0) {
				up++;
			} else {
				low++;
			}
		}

		boolean result = false;
		if ('Z' - i >= 0) {
			result = (low == word.length() - 1);
		}
		return up == word.length() || low == word.length() || result;
	}

	/**
	 * BFS
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> PrintFromTopToBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode poll = queue.poll();
				list.add(poll.val);
				if (poll.left != null)
					queue.offer(poll.left);
				if (poll.right != null)
					queue.offer(poll.right);
			}
		}
		return list;
	}

	@SuppressWarnings("unused")
	private TreeNode createBST() {
		TreeNode node = new TreeNode(5);
		node.left = new TreeNode(4);
		node.right = new TreeNode(7);
		node.left.left = new TreeNode(1);
		node.left.right = new TreeNode(3);
		node.right.left = new TreeNode(6);
		node.right.right = new TreeNode(8);
		return node;
	}

	/**
	 * 阿里巴巴国际站的股票代码是1688，这个数字具有这样的特性， 首先是个首位为1的4位数，其次恰巧有且仅有1个数字出现了两次。
	 * 类似的数字还有：1861,1668等。这样的数字一共有()个。
	 * 
	 * @return
	 */
	public int method1() {

		return 0;
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		if (strs.length == 0)
			return result;

		List<String> list = new ArrayList<>();
		result.add(list);
		list.add(strs[0]);
		boolean flag = true;
		for (int i = 1; i < strs.length; i++) {
			for (List<String> li : result) {
				if (isCommon(strs[i], li.get(0))) {
					li.add(strs[i]);
					flag = false;
					break;
				}
			}
			if (flag) {
				flag = true;
				List<String> l = new ArrayList<>();
				result.add(l);
				l.add(strs[i]);
			}
		}
		return result;
	}

	private boolean isCommon(String s1, String s2) {
		byte[] b1 = s1.getBytes();
		byte[] b2 = s2.getBytes();
		Arrays.sort(b1);
		Arrays.sort(b2);
		if (b1.length != b2.length)
			return false;
		for (int i = 0; i < b2.length; i++) {
			if (b1[i] != b2[i]) {
				return false;
			}
		}
		return true;
	}

	public boolean compareStrings(String A, String B) {
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < A.length(); i++) {
			list.add(A.charAt(i));
		}
		for (int i = 0; i < B.length(); i++) {
			if (list.contains(B.charAt(i))) {
				list.remove((Character) B.charAt(i));
			} else {
				return false;
			}
		}
		return true;
	}

	public int strStr(String source, String target) {
		if (source == null || target == null)
			return -1;
		int sourceSize = source.length();
		int tarSize = target.length();
		if (tarSize == 0)
			return 0;
		int f = 0;
		String s = null;
		for (int i = 0; i <= sourceSize; i++) {
			if (i + tarSize > sourceSize)
				return -1;
			if ((s = source.substring(i, i + tarSize)).equals(target)) {
				return i;
			}
		}
		return -1;
	}

	public int binarySearch(int[] nums, int target) {
		int lo = 0;
		int hi = nums.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] > target) {
				hi = mid - 1;
			} else if (nums[mid] < target) {
				lo = mid + 1;
			} else {
				if (mid == 0)
					return 0;
				while (nums[--mid] == target) {
				}
				return mid + 1;
			}
		}
		return -1;
	}

	public int longestPalindrome(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		}
		int result = 0;
		boolean flag = true;
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() % 2 == 0) {
				result += entry.getValue();
			} else if (flag && entry.getValue() % 2 != 0) {
				result += entry.getValue();
				flag = false;
			} else {
				result += (entry.getValue() - 1);
			}

		}
		return result;
	}

	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		String[] split = s.split(" ");
		for (String string : split) {
			sb.append(reverWord(string) + " ");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	private String reverWord(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	public int majorityElement(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int result = 0;
		for (int i : nums) {
			Integer put = map.put(i, map.getOrDefault(i, 0) + 1);
			if(put == null) put = 0;
			result = Math.max(result, put + 1);
		}
		for (Map.Entry<Integer, Integer> en : map.entrySet()) {
			if(en.getValue() == result){
				return  en.getKey();
			}
		}
		return 0;
	}

	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int result = getNum(l1)+getNum(l2);
        String s = String.valueOf(result);
        ListNode node = new ListNode((int)s.charAt(0));
        ListNode re = node;
        for(int i=1;i<s.length();i++){
        	node.next = new ListNode((int)s.charAt(i));
        	node = node.next;
        }
        return re;
    }
    private int getNum(ListNode node){
        int num = 0;
        while(node != null){
            num *= 10;
            num += node.val;
            node = node.next;
        }
        return num;
    }
	@Test
	public void test() {
		int majorityElement = majorityElement(new int[]{3,3,4});
		System.out.println(majorityElement);
		//String s = reverseWords("Let's take LeetCode contest");
		//System.out.println(s);
		// longestPalindrome("abccccdd");
		// binarySearch(new int[] { 4, 5, 9, 9, 12, 13, 14, 15, 15, 18 }, 10);
		// strStr("abcde", "e");
		// compareStrings("ABC", "B");
		// String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		// groupAnagrams(strs);
		// isValid("[])");
		// int[] arr = new int[] { 1, 2 };
		// rotate(arr, 1);
		// findTheDifference("abcd","aabcd");
		// HashMap<String, String> map = new HashMap<>();
		// map.put("name", "mxh");
		// String orDefault = map.getOrDefault("name", "0");
		// System.out.println(orDefault);
		// Stack s = new Stack<>();
		// subsets(new int[]{1,2,3});
		// testForeach();
		// System.out.println(findComplement(6));
		// findDuplicates(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 });
		// detectCapitalUse("Leetcode");
		// int parseInt2 = Integer.parseInt("ffffffff", 16);
		// System.out.println(parseInt2);
		// int parseIntq = Integer.parseInt("80000000", 16);
		// System.out.println(parseIntq);
		// int[] A = new int[] { 4, 3, 6, 7, 9, 1, 3, 2, 8, 5 };
		// quickSort(A, 0, A.length - 1);
		// for (int i : A) {
		// System.out.print(i + "-");
		// }
		int i = 0;

	}

}
