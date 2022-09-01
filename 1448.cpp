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
    int findGoodNodes(TreeNode* root, int largest) {
        if (root == nullptr) {
            return 0;
        }
        
        int l, r; 
        l = findGoodNodes(root->left, max(root->val, largest));
        r = findGoodNodes(root->right, max(root->val, largest));
        if (root->val >= largest) {
            return l + r + 1;
        }
        return l + r;
    }
    int goodNodes(TreeNode* root) {
        return findGoodNodes(root, root->val);
    }
};
