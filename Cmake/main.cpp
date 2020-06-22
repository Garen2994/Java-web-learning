#include <iostream>

//先序遍历递归实现算法
template <typename T,typename VST>//元素类型、操作器
void traverse(BinNodePosi(T) x,VST & visit){
    if(!x) return;
    visit(x->data);//访问当前节点
    traverse(x->lchild,visit);//递归访问左子树
    traverse(x->rchild,visit);
}//T(n)=O(1)+T(a)+T(n-a-1)=O(n)

