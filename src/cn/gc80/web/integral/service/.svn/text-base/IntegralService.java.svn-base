package cn.gc80.web.integral.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import cn.gc80.base.util.ConfigUtil;
import cn.gc80.datamodel.integral.IntContrast;
import cn.gc80.datamodel.integral.IntIntegral;
import cn.gc80.datamodel.sign.Sign;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.web.integral.dao.IntegralDao;
import cn.gc80.web.user.dao.UserDao;

@Service("integralService")
public class IntegralService {
	@Resource
	private IntegralDao integralDao;
	@Resource
	private  UserDao userDao;
	
	public void addIntegral(String codeNo, String userId, String ip,String equipment) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (codeNo != null && "gc001".equals(codeNo)) {
			if (userId != null && !"".equals(userId)) {
				IntContrast intContrast = integralDao.findContrastByCodeNo(codeNo);
				if (intContrast != null && !"".equals(intContrast)) {
					String startTime = intContrast.getStarttime();
					String endTime = intContrast.getEndtime();
					Long integral_val = intContrast.getIntegral();
					// 判断是否有时间限制
					if (startTime != null && endTime != null) {
						Date d;
						try {
							d = df.parse(df.format(new Date()));
							Date sta = df.parse(startTime);
							Date end = df.parse(endTime);
							long dl1 = d.getTime() - sta.getTime();
							long dl2 = end.getTime() - d.getTime();
							if (dl1 >= 0 && dl2 >= 0) {
								Long rate = intContrast.getRate();
								integral_val = integral_val * rate;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					SysUser sysUser = userDao.findUserByUserId(userId);
					if (sysUser != null && !"".equals(sysUser)) {
						if (intContrast != null && !"".equals(intContrast)) {
							IntIntegral intIntegral = new IntIntegral();
							intIntegral.setSysUser(sysUser);
							intIntegral.setIntContrast(intContrast);
							intIntegral.setCreatetime(df.format(new Date()));
							intIntegral.setDelflag("02");
							intIntegral.setDescribe(intContrast.getIntegralItem());
							intIntegral.setEquipment(equipment);
							intIntegral.setIp(ip);
							intIntegral.setIntegral(integral_val);
							intIntegral.setExpressId("");
							intIntegral.setType("01");
							intIntegral.setSysUserHelp(null);
							intIntegral.setCurrentIntegral(integral_val);
							integralDao.saveIntIntegral(intIntegral);
							sysUser.setTotalIntegral(integral_val);
							sysUser.setSurplusIntegral(integral_val);
							userDao.saveUser(sysUser);
						}
					}
				}
			}
		}else if(codeNo != null && "gc004".equals(codeNo)){
			if (userId != null && !"".equals(userId)) {
				IntContrast intContrast = integralDao.findContrastByCodeNo(codeNo);
				if (intContrast != null && !"".equals(intContrast)) {
					String startTime = intContrast.getStarttime();
					String endTime = intContrast.getEndtime();
					Long integral_val = intContrast.getIntegral();
					// 判断是否有时间限制
					if (startTime != null && endTime != null) {
						Date d;
						try {
							d = df.parse(df.format(new Date()));
							Date sta = df.parse(startTime);
							Date end = df.parse(endTime);
							long dl1 = d.getTime() - sta.getTime();
							long dl2 = end.getTime() - d.getTime();
							if (dl1 >= 0 && dl2 >= 0) {
								Long rate = intContrast.getRate();
								integral_val = integral_val * rate;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					SysUser sysUser = userDao.findUserByUserId(userId);
					if (sysUser != null && !"".equals(sysUser)) {
						Double levelRate = this.findLevelRateByTotalIntegral(sysUser.getTotalIntegral());
						integral_val = new BigDecimal(integral_val * levelRate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						if (intContrast != null && !"".equals(intContrast)) {
							IntIntegral intIntegral = new IntIntegral();
							intIntegral.setSysUser(sysUser);
							intIntegral.setIntContrast(intContrast);
							intIntegral.setCreatetime(df.format(new Date()));
							intIntegral.setDelflag("02");
							intIntegral.setDescribe(intContrast.getIntegralItem());
							intIntegral.setEquipment(equipment);
							intIntegral.setIp(ip);
							intIntegral.setIntegral(integral_val);
							intIntegral.setExpressId("");
							intIntegral.setType("01");
							intIntegral.setSysUserHelp(null);
							intIntegral.setCurrentIntegral(sysUser.getSurplusIntegral() + integral_val);
							integralDao.saveIntIntegral(intIntegral);
							sysUser.setTotalIntegral(sysUser.getTotalIntegral()+ integral_val);
							sysUser.setSurplusIntegral(sysUser.getSurplusIntegral() + integral_val);
							userDao.saveUser(sysUser);
						}
					}
				}
			}
		} else if(codeNo != null && "gc003".equals(codeNo)){
			IntContrast intContrast = integralDao.findContrastByCodeNo(codeNo);
			if (intContrast != null && !"".equals(intContrast)) {
				String startTime = intContrast.getStarttime();
				String endTime = intContrast.getEndtime();
				Long integral_val = intContrast.getIntegral();
				// 判断是否有时间限制
				if (startTime != null && endTime != null) {
					Date d;
					try {
						d = df.parse(df.format(new Date()));
						Date sta = df.parse(startTime);
						Date end = df.parse(endTime);
						long dl1 = d.getTime() - sta.getTime();
						long dl2 = end.getTime() - d.getTime();
						if (dl1 >= 0 && dl2 >= 0) {
							Long rate = intContrast.getRate();
							integral_val = integral_val * rate;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				// 当前用户的信息
				SysUser user = userDao.findUserByUserId(userId);
				// 总积分
				Double levelRate = this.findLevelRateByTotalIntegral(user.getTotalIntegral());
				integral_val = new BigDecimal(integral_val * levelRate)
						.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
				if (user != null && !"".equals(user)) {
					// 判断是否已经赠送过积分
					IntIntegral intInte = integralDao.findIntIntegral1(
							userId, intContrast.getId());
					if (intInte != null && !"".equals(intInte)) {
						return ;
					} else {
						IntIntegral intIntegral = new IntIntegral();
						intIntegral.setSysUser(user);
						intIntegral.setIntContrast(intContrast);
						intIntegral.setCreatetime(df.format(new Date()));
						intIntegral.setDelflag("02");
						intIntegral.setDescribe(intContrast.getIntegralItem());
						intIntegral.setEquipment(equipment);
						intIntegral.setIp(ip);
						intIntegral.setIntegral(integral_val);
						intIntegral.setExpressId("");
						intIntegral.setType("01");
						intIntegral.setSysUserHelp(null);
						intIntegral.setCurrentIntegral(user
								.getSurplusIntegral() + integral_val);
						integralDao.saveIntIntegral(intIntegral);
						user.setTotalIntegral(user.getTotalIntegral()
								+ integral_val);
						user.setSurplusIntegral(user.getSurplusIntegral()
								+ integral_val);
						userDao.saveUser(user);
						}
					}
				}
			}else if (codeNo != null && "gc005".equals(codeNo)) {
				if (userId != null && !"".equals(userId)) {
					IntContrast intContrast = integralDao
							.findContrastByCodeNo(codeNo);
					if (intContrast != null && !"".equals(intContrast)) {
						String startTime = intContrast.getStarttime();
						String endTime = intContrast.getEndtime();
						Long integral_val = intContrast.getIntegral();
						// 判断是否有时间限制
						if (startTime != null && endTime != null) {
							Date d;
							try {
								d = df.parse(df.format(new Date()));
								Date sta = df.parse(startTime);
								Date end = df.parse(endTime);
								long dl1 = d.getTime() - sta.getTime();
								long dl2 = end.getTime() - d.getTime();
								if (dl1 >= 0 && dl2 >= 0) {
									Long rate = intContrast.getRate();
									integral_val = integral_val * rate;
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}

						SysUser sysUser = userDao.findUserByUserId(userId);
						Double levelRate = this.findLevelRateByTotalIntegral(sysUser.getTotalIntegral());
						integral_val = new BigDecimal(integral_val * levelRate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						if (sysUser != null && !"".equals(sysUser)) {
							if (intContrast != null && !"".equals(intContrast)) {
								IntIntegral intIntegral = new IntIntegral();
								intIntegral.setSysUser(sysUser);
								intIntegral.setIntContrast(intContrast);
								intIntegral.setCreatetime(df.format(new Date()));
								intIntegral.setDelflag("02");
								intIntegral.setDescribe("首日签到奖励" + integral_val
										+ "分");
								intIntegral.setEquipment(equipment);
								intIntegral.setIp(ip);
								intIntegral.setIntegral(integral_val);
								intIntegral.setExpressId("");
								intIntegral.setType("01");
								intIntegral.setSysUserHelp(null);
								intIntegral.setCurrentIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								integralDao.saveIntIntegral(intIntegral);
								sysUser.setTotalIntegral(sysUser.getTotalIntegral()
										+ integral_val);
								sysUser.setSurplusIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								userDao.saveUser(sysUser);
								Sign sign = new Sign();
								sign.setSysUser(sysUser);
								sign.setReward(integral_val);
								sign.setTime(df.format(new Date()));
								sign.setEquipment(equipment);
								sign.setDescribe("首日签到奖励" + integral_val + "分");
								userDao.saveSign(sign);

							}
						}
					}
				}

				// 5天
			} else if (codeNo != null && "gc006".equals(codeNo)) {
				if (userId != null && !"".equals(userId)) {
					IntContrast intContrast = integralDao
								.findContrastByCodeNo(codeNo);
					if (intContrast != null && !"".equals(intContrast)) {
						String startTime = intContrast.getStarttime();
						String endTime = intContrast.getEndtime();
						Long integral_val = intContrast.getIntegral();
						// 判断是否有时间限制
						if (startTime != null && endTime != null) {
							Date d;
							try {
								d = df.parse(df.format(new Date()));
								Date sta = df.parse(startTime);
								Date end = df.parse(endTime);
								long dl1 = d.getTime() - sta.getTime();
								long dl2 = end.getTime() - d.getTime();
								if (dl1 >= 0 && dl2 >= 0) {
									Long rate = intContrast.getRate();
									integral_val = integral_val * rate;
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}

						SysUser sysUser = userDao.findUserByUserId(userId);
						Double levelRate = this.findLevelRateByTotalIntegral(sysUser.getTotalIntegral());
						integral_val = new BigDecimal(integral_val * levelRate)
								.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						if (sysUser != null && !"".equals(sysUser)) {
							if (intContrast != null && !"".equals(intContrast)) {
								IntIntegral intIntegral = new IntIntegral();
								intIntegral.setSysUser(sysUser);
								intIntegral.setIntContrast(intContrast);
								intIntegral.setCreatetime(df.format(new Date()));
								intIntegral.setDelflag("02");
								intIntegral.setDescribe(intContrast
										.getIntegralItem()
										+ "奖励"
										+ integral_val
										+ "分");
								intIntegral.setEquipment(equipment);
								intIntegral.setIp(ip);
								intIntegral.setIntegral(integral_val);
								intIntegral.setExpressId("");
								intIntegral.setType("01");
								intIntegral.setSysUserHelp(null);
								intIntegral.setCurrentIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								integralDao.saveIntIntegral(intIntegral);
								sysUser.setTotalIntegral(sysUser.getTotalIntegral()
										+ integral_val);
								sysUser.setSurplusIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								userDao.saveUser(sysUser);
								Sign sign = new Sign();
								sign.setSysUser(sysUser);
								sign.setReward(integral_val);
								sign.setTime(df.format(new Date()));
								sign.setEquipment(equipment);
								sign.setDescribe(intContrast.getIntegralItem()
										+ "奖励" + integral_val + "分");
								userDao.saveSign(sign);
							}
						}
					}
				}
				// 10天
			} else if (codeNo != null && "gc007".equals(codeNo)) {
				if (userId != null && !"".equals(userId)) {
					IntContrast intContrast = integralDao
							.findContrastByCodeNo(codeNo);
					if (intContrast != null && !"".equals(intContrast)) {
						String startTime = intContrast.getStarttime();
						String endTime = intContrast.getEndtime();
						Long integral_val = intContrast.getIntegral();
						// 判断是否有时间限制
						if (startTime != null && endTime != null) {
							Date d;
							try {
								d = df.parse(df.format(new Date()));
								Date sta = df.parse(startTime);
								Date end = df.parse(endTime);
								long dl1 = d.getTime() - sta.getTime();
								long dl2 = end.getTime() - d.getTime();
								if (dl1 >= 0 && dl2 >= 0) {
									Long rate = intContrast.getRate();
									integral_val = integral_val * rate;
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}

						SysUser sysUser = userDao.findUserByUserId(userId);
						Double levelRate = this.findLevelRateByTotalIntegral(sysUser.getTotalIntegral());
						integral_val = new BigDecimal(integral_val * levelRate)
								.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						if (sysUser != null && !"".equals(sysUser)) {
							if (intContrast != null && !"".equals(intContrast)) {
								IntIntegral intIntegral = new IntIntegral();
								intIntegral.setSysUser(sysUser);
								intIntegral.setIntContrast(intContrast);
								intIntegral.setCreatetime(df.format(new Date()));
								intIntegral.setDelflag("02");
								intIntegral.setDescribe(intContrast
										.getIntegralItem()
										+ "奖励"
										+ integral_val
										+ "分");
								intIntegral.setEquipment(equipment);
								intIntegral.setIp(ip);
								intIntegral.setIntegral(integral_val);
								intIntegral.setExpressId("");
								intIntegral.setType("01");
								intIntegral.setSysUserHelp(null);
								intIntegral.setCurrentIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								integralDao.saveIntIntegral(intIntegral);
								sysUser.setTotalIntegral(sysUser.getTotalIntegral()
										+ integral_val);
								sysUser.setSurplusIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								userDao.saveUser(sysUser);
								Sign sign = new Sign();
								sign.setSysUser(sysUser);
								sign.setReward(integral_val);
								sign.setTime(df.format(new Date()));
								sign.setEquipment(equipment);
								sign.setDescribe(intContrast.getIntegralItem()
										+ "奖励" + integral_val + "分");
								userDao.saveSign(sign);
							}
						}
					}
				}
				// 20天
			} else if (codeNo != null && "gc008".equals(codeNo)) {
				if (userId != null && !"".equals(userId)) {
					IntContrast intContrast = integralDao
							.findContrastByCodeNo(codeNo);
					if (intContrast != null && !"".equals(intContrast)) {
						String startTime = intContrast.getStarttime();
						String endTime = intContrast.getEndtime();
						Long integral_val = intContrast.getIntegral();
						// 判断是否有时间限制
						if (startTime != null && endTime != null) {
							Date d;
							try {
								d = df.parse(df.format(new Date()));
								Date sta = df.parse(startTime);
								Date end = df.parse(endTime);
								long dl1 = d.getTime() - sta.getTime();
								long dl2 = end.getTime() - d.getTime();
								if (dl1 >= 0 && dl2 >= 0) {
									Long rate = intContrast.getRate();
									integral_val = integral_val * rate;
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}

						SysUser sysUser = userDao.findUserByUserId(userId);
						Double levelRate = this.findLevelRateByTotalIntegral(sysUser.getTotalIntegral());
						integral_val = new BigDecimal(integral_val * levelRate)
								.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						if (sysUser != null && !"".equals(sysUser)) {
							if (intContrast != null && !"".equals(intContrast)) {
								IntIntegral intIntegral = new IntIntegral();
								intIntegral.setSysUser(sysUser);
								intIntegral.setIntContrast(intContrast);
								intIntegral.setCreatetime(df.format(new Date()));
								intIntegral.setDelflag("02");
								intIntegral.setDescribe(intContrast
										.getIntegralItem()
										+ "奖励"
										+ integral_val
										+ "分");
								intIntegral.setEquipment(equipment);
								intIntegral.setIp(ip);
								intIntegral.setIntegral(integral_val);
								intIntegral.setExpressId("");
								intIntegral.setType("01");
								intIntegral.setSysUserHelp(null);
								intIntegral.setCurrentIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								integralDao.saveIntIntegral(intIntegral);
								sysUser.setTotalIntegral(sysUser.getTotalIntegral()
										+ integral_val);
								sysUser.setSurplusIntegral(sysUser
										.getSurplusIntegral() + integral_val);
								userDao.saveUser(sysUser);
								Sign sign = new Sign();
								sign.setSysUser(sysUser);
								sign.setReward(integral_val);
								sign.setTime(df.format(new Date()));
								sign.setEquipment(equipment);
								sign.setDescribe(intContrast.getIntegralItem()
										+ "奖励" + integral_val + "分");
								userDao.saveSign(sign);
							}
						}
					}
				}

			}
		
	}
	
	//根据积分得到倍率
	public Double findLevelRateByTotalIntegral(Long integral){
		Double levelRate = 1d;
		// 学霸的积分
		Long oneLevelIntegral = Long.parseLong(ConfigUtil.getConfig("one_level_integral"));
		// 大学生的积分
		Long twoLevelIntegral = Long.parseLong(ConfigUtil.getConfig("two_level_integral"));
		// 中学生的积分
		Long threeLevelIntegral = Long.parseLong(ConfigUtil.getConfig("three_level_integral"));
		// 小学生的积分
		Long fourLevelIntegral = Long.parseLong(ConfigUtil.getConfig("four_level_integral"));
		// 等级倍率
		if (integral >= oneLevelIntegral) {
			levelRate = Double.valueOf(ConfigUtil.getConfig("one_level_rate"));
		} else if (integral >= twoLevelIntegral) {
			levelRate = Double.valueOf(ConfigUtil.getConfig("two_level_rate"));
		} else if (integral >= threeLevelIntegral) {
			levelRate = Double.valueOf(ConfigUtil.getConfig("three_level_rate"));
		} else if (integral >= fourLevelIntegral) {
			levelRate = Double.valueOf(ConfigUtil.getConfig("four_level_rate"));
		}
		return levelRate;
	}
	
	public IntIntegral findIntegral(String userId, String codeNo, String time) {
		return this.integralDao.findIntegral(userId, codeNo, time);
	}
	
	public IntContrast getContrast(String codeNo) {
		return this.integralDao.getContrast(codeNo);
	}

	public String saveIntegral(IntIntegral integral) {
		return this.integralDao.saveIntegral(integral);
	}
	
}
