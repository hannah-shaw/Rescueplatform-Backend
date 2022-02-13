package com.rescueplatform_backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.Employee;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.mapper.EmployeeMapper;
import com.rescueplatform_backend.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 分页获取员工列表
     *
     * @param currentPage 当前页
     * @param size        页面条数
     * @param employee    操作对象
     * @return
     */
    @Override
    public RespPageBean getEmployeeList(Integer currentPage, Integer size, Employee employee, String startDate, String endDate) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeePage = employeeMapper.getEmployeePage(page, employee, startDate, endDate);
        // employeePage.getTotal() 总记录数， employeePage.getRecords() 查询出来的集合(记录)
        RespPageBean respPageBean = new RespPageBean(employeePage.getTotal(), employeePage.getRecords());
        return respPageBean;
    }

    /**
     * 获取工号
     *
     * @return
     */
    @Override
    public RespBean getMaxWorkID() {
        // 获取员工编号最大值
        String maxWorkID = employeeMapper.getMaxWorkID();
        if (maxWorkID == null || maxWorkID.equals("")){
            maxWorkID = "0001";
        }
        //转换成 integer 将最大的 员工id 加1
        String max = "1" + maxWorkID;
        Integer i1 = Integer.parseInt(max) + 1;

        //再次转换为 String 类型, 返回前端，调下一个接口将 String 类型的编号存入数据库
        String s2 = i1.toString();
        //截取 1 之后的字符串 员工编号还是8位 以 0000001 格式
        String newID = s2.substring(1);
        return RespBean.success(null, newID);
    }

    @Override
    public RespBean getMaxWorkID2() {
        // 获取员工编号最大值
        String maxWorkID = employeeMapper.getMaxWorkID();
        if (maxWorkID == null || maxWorkID.equals("")){
            maxWorkID = "0001";
        }
        // 转换成 integer 将最大的 员工id 加 1 ， 可以保证编号唯一切自增
        Integer i = Integer.parseInt(maxWorkID) + 1;
        // 格式化用0补齐前面的位数 编号8位数 ， 该方法只能传入数字，自动格式化后转换为 String 类型
        String format = String.format("%08d", i);
        return RespBean.success(null,format);
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmp(Employee employee) {
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        //计算 合同 开始日期 到 结束日期 一共多少天 beginDate：开始日期 endContract：结束日期
        // 总天数
        long totalDays = beginContract.until(endContract, ChronoUnit.DAYS);
        // 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        // 合同年限
        String format = decimalFormat.format(totalDays / 365.00);
        // 总年数
        Double totalYears = Double.parseDouble(format);
        // 合同期限
        employee.setContractTerm(totalYears);
        // insert 方法返回受影响的行数，受影响的行数如果为1，则说明成功
        if (employeeMapper.insert(employee) == 1){
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败！");
    }
}
