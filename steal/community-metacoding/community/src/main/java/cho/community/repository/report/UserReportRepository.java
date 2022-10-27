package cho.community.repository.report;

import cho.community.entity.report.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReportRepository extends JpaRepository<UserReport, Integer> {
    UserReport findByReporterIdAndReportedUserId(int reporterId, int reportedUserId);
    List<UserReport> findByReportedUserId(int reportedId);
    void deleteAllByReportedUserId(int id);
}