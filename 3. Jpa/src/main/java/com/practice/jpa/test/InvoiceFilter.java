package com.practice.jpa.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스는 도메인과 인프라가 결합된 코드이다.
 * private 에는 인프라 코드가 있으며 public에는 이 인프라 코드를 이용한다.
 * 이렇게 도메인과 인프라 코드가 결합되면 테스트하기가 어려워진다. 이 아래의 코드는 비교적 간단한 예시이다. 단순히 송장을 모두 가져와서 반환한다.
 * 하지만 복잡한 인프라, 도메인 코드가 만들어진다면 점점 테스트하기 힘들어진다. 각각의 책임이 결합하여 더 복잡한 상황을 테스트해야 한다. 그렇기 때문에
 * 책임에 대한 분리가 필요하다. 이를 위해서 port-adaptor 패턴을 권장한다. 헥사고날 아키텍처에서 사용되며 도메인에서는 포트를 통해 인프라에 요청한다.
 * 구현 세부사항은 어댑터에 정의되어 있다.
 *
 * 포트를 통한 분리를 사용하여 도메인과 인프라를 분리한다.
 */
public class InvoiceFilter {
	public List<Invoice> lowValueInvoices() {
		List<Invoice> issuedInvoices = all();
		assert issuedInvoices != null;
		return issuedInvoices.stream()
			.filter(invoice -> invoice.getValue() < 100)
			.toList();
	}

	private List<Invoice> all() {
		try{
			Connection connection = DriverManager.getConnection("db", "root", "");
			PreparedStatement ps = connection.prepareStatement("select * from invoice");
			ResultSet rs = ps.executeQuery();

			List<Invoice> allInvoices = new ArrayList<>();
			while(rs.next()) {
				allInvoices.add(new Invoice(
					rs.getString("name"), rs.getInt("value")));
			}

			ps.close();
			connection.close();

			return allInvoices;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
