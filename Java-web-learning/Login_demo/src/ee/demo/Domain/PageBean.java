package ee.demo.Domain;
/**
 * @Title : 分页工具
 * @Author : Garen
 * @Date : 2019/12/12 20:33
 */
import java.util.List;

public class PageBean<T> {
    private int totalCount;  //总记录数
    private int totalPage;   //总页数
    private List<T> list;   //分页列表
    private int currentPage; //当前页码
    private int row;   //每页记录数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", row=" + row +
                '}';
    }
}
