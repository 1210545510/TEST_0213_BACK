package com.aml.api.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.model.TaskModel.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aml.api.service.SnakerEngineFacets;

/**
 * 
 * @className: TaskController
 * @description: 任务管理
 * @author huangliangbao
 * @date 2018年1月2日 上午11:27:00
 *
 */
@Controller
@RequestMapping(value = "/snaker/task")
public class TaskController {
	@Autowired
	private SnakerEngineFacets facets;

	/**
	 * 
	 * @title: homeTaskList
	 * @description: 待办任务列表
	 *
	 * @param model
	 * @return
	 * @date 2017年11月23日 下午3:39:57
	 */
	@RequestMapping(value = "active", method = RequestMethod.GET)
	public String homeTaskList ( Model model ) {
		// TODO 业务处理： 根据当前用户id，获取用户名(当前处理人保存的用户名)

		// List<String> list = ShiroUtils.getGroups();
		// list.add(ShiroUtils.getUsername());
		// log.info(list.toString());
		// String[] assignees = new String[list.size()];
		// list.toArray(assignees);

		// 修改为：[Admin, 200, admin]
		String[] assignees = new String[] { "snaker", "200200", "snaker" };

		// 只需要这一个任务列表
		Page<WorkItem> majorPage = new Page<WorkItem>(5);
		Page<WorkItem> aidantPage = new Page<WorkItem>(3);
		Page<HistoryOrder> ccorderPage = new Page<HistoryOrder>(3);
		List<WorkItem> majorWorks = facets.getEngine().query().getWorkItems(majorPage,
				new QueryFilter().setOperators(assignees).setTaskType(TaskType.Major.ordinal()));
		List<WorkItem> aidantWorks = facets.getEngine().query().getWorkItems(aidantPage,
				new QueryFilter().setOperators(assignees).setTaskType(TaskType.Aidant.ordinal()));
		List<HistoryOrder> ccWorks = facets.getEngine().query().getCCWorks(ccorderPage,
				new QueryFilter().setOperators(assignees).setState(1));

		model.addAttribute("majorWorks", majorWorks);
		model.addAttribute("majorTotal", majorPage.getTotalCount());
		model.addAttribute("aidantWorks", aidantWorks);
		model.addAttribute("aidantTotal", aidantPage.getTotalCount());
		model.addAttribute("ccorderWorks", ccWorks);
		model.addAttribute("ccorderTotal", ccorderPage.getTotalCount());
		return "snaker/activeTask";
	}

	/**
	 * 根据当前用户查询待办任务列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String userTaskList ( Model model, Page<WorkItem> page ) {
		// facets.getEngine().query().getWorkItems(page, new QueryFilter().setOperator(ShiroUtils.getUsername()));
		facets.getEngine().query().getWorkItems(page, new QueryFilter().setOperator("snaker"));
		model.addAttribute("page", page);
		return "snaker/userTask";
	}

	@RequestMapping(value = "actor/add", method = RequestMethod.GET)
	public String addTaskActor ( Model model, String orderId, String taskName ) {
		model.addAttribute("orderId", orderId);
		model.addAttribute("taskName", taskName);
		return "snaker/actor";
	}

	/**
	 * 
	 * @title: addTaskActor
	 * @description: TODO
	 *
	 * @param model
	 * @param orderId
	 * @param taskName
	 * @param operator
	 * @return
	 * @date 2017年11月24日 上午11:35:55
	 */
	@RequestMapping(value = "actor/add", method = RequestMethod.POST)
	@ResponseBody
	public String addTaskActor ( Model model, String orderId, String taskName, String operator ) {
		List<Task> tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
		for (Task task : tasks) {
			if (task.getTaskName().equalsIgnoreCase(taskName) && StringUtils.isNotEmpty(operator)) {
				facets.getEngine().task().addTaskActor(task.getId(), operator);
				// facets.getEngine().task().removeTaskActor(task.getId(), actors[0]);
			}
		}
		return "success";
	}

	/**
	 * 
	 * @title: autoComplete
	 * @description: 根据实例ID，找出任务ID ， 没被抽样的Waived任务，自动结束
	 *
	 * @param model
	 * @param orderId
	 * @param taskName
	 * @param operator
	 * @return
	 * @date 2017年11月24日 上午11:35:55
	 */
	@RequestMapping(value = "waived/complete", method = RequestMethod.POST)
	@ResponseBody
	public String autoComplete ( Model model, String orderId, String taskName, String operator ) {
		List<Task> tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
		for (Task task : tasks) {
			// 手动结束（不行，部分数据没有修改）
			// facets.getEngine().task().complete(task.getId(), operator);
			// 手动执行任务进行结束（可行）
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("remark", "没被抽样的Waived任务，自动结束");
			facets.execute(task.getId(), "QA.Waived.operator", params);
		}
		return "success";
	}

	/**
	 * 
	 * @title: autoNewTask
	 * @description: 根据实例ID，找出任务ID ， 被抽样的Waived任务，自动产生新任务开始
	 *
	 * @param model
	 * @param orderId
	 * @param taskName
	 * @param operator
	 * @return
	 * @date 2017年11月24日 上午11:35:55
	 */
	@RequestMapping(value = "waived/newTask", method = RequestMethod.POST)
	@ResponseBody
	public String autoNewTask ( Model model, String orderId, String taskName, String operator ) {
		List<Task> tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
		for (Task task : tasks) {
			// 1、根据实例ID，找出被锁定的任务ID，设置处理人
			// facets.getEngine().task().removeTaskActor(task.getId(), actors[0]);
			// 1、根据实例ID，找出被锁定的任务ID，重新生成新任务
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("remark", "被抽样的Waived任务，自动产生新任务开始");
			facets.getEngine().task().createNewTask(task.getId(), 0, "snaker");
		}
		return "success";
	}

	@RequestMapping(value = "tip", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> addTaskActor ( String orderId, String taskName ) {
		List<Task> tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
		StringBuilder builder = new StringBuilder();
		String createTime = "";
		for (Task task : tasks) {
			if (task.getTaskName().equalsIgnoreCase(taskName)) {
				String[] actors = facets.getEngine().query().getTaskActorsByTaskId(task.getId());
				for (String actor : actors) {
					builder.append(actor).append(",");
				}
				createTime = task.getCreateTime();
			}
		}
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		Map<String, String> data = new HashMap<String, String>();
		data.put("actors", builder.toString());
		data.put("createTime", createTime);
		return data;
	}

	/**
	 * 活动任务查询列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "active/more", method = RequestMethod.GET)
	public String activeTaskList ( Model model, Page<WorkItem> page, Integer taskType ) {
		// List<String> list = ShiroUtils.getGroups();
		// list.add(ShiroUtils.getUsername());
		// log.info(list.toString());
		// String[] assignees = new String[list.size()];
		// list.toArray(assignees);
		// facets.getEngine().query().getWorkItems(page, new
		// QueryFilter().setOperators(assignees).setTaskType(taskType));
		// model.addAttribute("page", page);
		// model.addAttribute("taskType", taskType);
		return "snaker/activeTaskMore";
	}

	/**
	 * 活动任务查询列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "active/ccmore", method = RequestMethod.GET)
	public String activeCCList ( Model model, Page<HistoryOrder> page ) {
		// List<String> list = ShiroUtils.getGroups();
		// list.add(ShiroUtils.getUsername());
		// log.info(list.toString());
		// String[] assignees = new String[list.size()];
		// list.toArray(assignees);
		// facets.getEngine().query().getCCWorks(page, new QueryFilter().setOperators(assignees).setState(1));
		// model.addAttribute("page", page);
		return "snaker/activeCCMore";
	}

	/**
	 * 
	 * @title: activeTaskExec
	 * @description: 模拟当前任务的执行（让任务进入到下一节点）
	 *
	 * @param model
	 * @param taskId
	 * @return
	 * @date 2017年11月23日 下午4:58:06
	 */
	@RequestMapping(value = "exec", method = RequestMethod.POST)
	public String activeTaskExec ( Model model, String taskId ) {
		// facets.execute(taskId, ShiroUtils.getUsername(), null);
		// TODO 业务处理，获取角色下所有人，随机分配一个人
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("测试审批", "下级处理人怎么来");
		facets.execute(taskId, "snaker", jsonMap);
		return "redirect:/snaker/task/active";
	}

	/**
	 * 
	 * @title: activeTaskReject
	 * @description: 模拟当前活动任务的驳回
	 *
	 * @param model
	 * @param taskId
	 * @return
	 * @date 2017年11月23日 下午5:01:17
	 */
	@RequestMapping(value = "reject", method = RequestMethod.POST)
	public String activeTaskReject ( Model model, String taskId ) {
		String error = "";
		try {
			// facets.executeAndJump(taskId, ShiroUtils.getUsername(), null, null);
			facets.executeAndJump(taskId, "snaker", null, null);
		} catch (Exception e) {
			error = "?error=1";
		}
		return "redirect:/snaker/task/active" + error;
	}

	/**
	 * 历史完成任务查询列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "history", method = RequestMethod.GET)
	public String historyTaskList ( Model model, Page<WorkItem> page ) {
		facets.getEngine().query().getHistoryWorkItems(page, new QueryFilter().setOperator("admin"));
		model.addAttribute("page", page);
		return "snaker/historyTask";
	}

	/**
	 * 历史任务撤回
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "undo", method = RequestMethod.GET)
	public String historyTaskUndo ( Model model, String taskId ) {
		String returnMessage = "";
		try {
			facets.getEngine().task().withdrawTask(taskId, "admin");
			returnMessage = "任务撤回成功.";
		} catch (Exception e) {
			returnMessage = e.getMessage();
		}
		model.addAttribute("returnMessage", returnMessage);
		return "redirect:/snaker/task/history";
	}
}
