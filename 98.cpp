/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool isValidBSTLeftRight(TreeNode* root, long minVal, long maxVal) {
        if (root == nullptr) {
            return true;
        }
        
        if (minVal <= root->val and maxVal >= root->val) {
            return isValidBSTLeftRight(root->left, minVal, root->val - 1L) && isValidBSTLeftRight(root->right, root->val + 1L, maxVal);
        }
        return false;
    }
    
    bool isValidBST(TreeNode* root) {
        return isValidBSTLeftRight(root->left, LONG_MIN, root->val - 1L) && isValidBSTLeftRight(root->right, root->val + 1L, LONG_MAX);
    }
};
